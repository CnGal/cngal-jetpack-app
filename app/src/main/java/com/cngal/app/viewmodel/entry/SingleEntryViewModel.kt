package com.cngal.app.viewmodel.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.extension.toDate
import com.cngal.app.extension.toString
import com.cngal.app.model.entry.EntryModel
import com.cngal.app.model.entry.EntryType
import com.cngal.app.model.entry.OutlinkModel
import com.cngal.app.model.entry.PositionGeneralType
import com.cngal.app.model.entry.PublishPlatformType
import com.cngal.app.model.entry.getGeneralType
import com.cngal.app.model.periphery.PeripheryOverviewModel
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.EntryRepository
import com.cngal.app.uistate.entry.ReleaseUiState
import com.cngal.app.uistate.entry.SingleEntryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingleEntryViewModel : ViewModel()
{
    private val _entry = MutableStateFlow(ApiResponse.empty<EntryModel>())
    val entry = _entry.asStateFlow()

    private val _uiState = MutableStateFlow(SingleEntryUiState())
    val uiState = _uiState.asStateFlow()

    private val _peripheryOverview = MutableStateFlow(ApiResponse.empty<PeripheryOverviewModel>())
    val peripheryOverview = _peripheryOverview.asStateFlow()


    private fun getPeripheryOverviewData(id: Int)
    {
        viewModelScope.launch {
            EntryRepository.getPeripheryOverviewData(id).onStart {
                _peripheryOverview.value = ApiResponse.loading()
            }.catch { e ->
                _peripheryOverview.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _peripheryOverview.value = ApiResponse.success(model)
            }
        }
    }

    fun loadEntryData(id: Int?)
    {
        if (id == null || id <= 0)
        {
            return
        }

        getPeripheryOverviewData(id)

        viewModelScope.launch {
            EntryRepository.getEntryData(id).onStart {
                _entry.value = ApiResponse.loading()
            }.catch { e ->
                _entry.value =
                    ApiResponse.error(e)
            }.collect { model ->

                _uiState.update { state ->
                    //转换SteamId
                    if (model.type == EntryType.Game && model.releases.count { it.publishPlatformType == PublishPlatformType.Steam } > 0)
                    {
                        try
                        {
                            state.steamId =
                                model.releases.first { it.publishPlatformType == PublishPlatformType.Steam }.link?.toInt()
                        } catch (_: Throwable)
                        {
                        }
                    }

                    //合并相册
                    state.images.clear()
                    model.pictures.forEach {
                        state.images.addAll(it.content)
                    }

                    //转换关联列表
                    state.castWorks =
                        model.staffGames.filter { it.addInfors.count { it.contents.count { it.displayName.getGeneralType() == PositionGeneralType.CV } > 0 } > 0 }
                    state.productionGroupWorks =
                        model.staffGames.filter { it.addInfors.count { it.contents.count { it.displayName.getGeneralType() == PositionGeneralType.ProductionGroup } > 0 } > 0 }
                    state.publisherWorks =
                        model.staffGames.filter { it.addInfors.count { it.contents.count { it.displayName.getGeneralType() == PositionGeneralType.Publisher } > 0 && it.contents.count { it.displayName.getGeneralType() == PositionGeneralType.ProductionGroup } <= 0 } > 0 }

                    state.participationWorks =
                        model.staffGames.filter { it.addInfors.count { it.contents.count { it.displayName.getGeneralType() != PositionGeneralType.CV && it.displayName.getGeneralType() != PositionGeneralType.SpecialThanks } > 0 && it.contents.count { it.displayName.getGeneralType() == PositionGeneralType.ProductionGroup || it.displayName.getGeneralType() == PositionGeneralType.Publisher } <= 0 } > 0 }

                    state.appreciatedParticWorks =
                        model.staffGames.filter { it.addInfors.count { it.contents.count { it.displayName.getGeneralType() == PositionGeneralType.SpecialThanks } > 0 } > 0 }

                    state.games = model.entryRelevances.filter { it.type == EntryType.Game }

                    state.groups =
                        model.entryRelevances.filter { it.type == EntryType.ProductionGroup }

                    state.roles = model.entryRelevances.filter { it.type == EntryType.Role }

                    state.staffs = model.entryRelevances.filter { it.type == EntryType.Staff }

                    //动态
                    state.news = model.newsOfEntry.take(3)

                    //外部链接
                    state.outlinks.clear()
                    model.otherRelevances.forEach {
                        val image = when (it.displayName)
                        {
                            "萌娘百科" -> "Moegirl.png"
                            "Bangumi" -> "Bangumi.png"
                            "百度百科" -> "BaiDuWiki.png"
                            "2DFan" -> "2DFan.png"
                            "中文维基百科" -> "Wiki.png"
                            "月幕Galgame" -> "YMGal.png"
                            "Bilibili" -> "bilibili.png"
                            "bilibili" -> "bilibili.png"
                            "WikiData" -> "Wikidata.png"
                            "微博" -> "weibo.png"
                            "AcFun" -> "AcFun.png"
                            "知乎" -> "zhihu.png"
                            "爱发电" -> "Afdian.png"
                            "Pixiv" -> "pixiv.png"
                            "Twitter" -> "twitter.png"
                            "YouTube" -> "Youtube.png"
                            "Facebook" -> "Facebook.png"
                            "官网" -> "SmartHome.png"
                            "摩点" -> "modian.png"
                            else ->
                            {
                                null
                            }
                        }

                        state.outlinks.add(
                            OutlinkModel(
                                image = if (!image.isNullOrBlank())
                                {
                                    "https://res.cngal.org/_content/CnGalWebSite.Shared/images/${image}"
                                }
                                else
                                {
                                    it.image
                                },
                                displayValue = when (it.displayName)
                                {
                                    "VNDB" -> "VNDB.org力争成为一个关于视觉小说的全面的信息数据库"
                                    else ->
                                    {
                                        it.displayValue
                                    }
                                },
                                id = 0,
                                displayName = it.displayName,
                                link = when (it.displayName)
                                {
                                    "月幕Galgame" -> it.link?.replace(
                                        "www.ymgal.com",
                                        "www.ymgal.games"
                                    )

                                    else ->
                                    {
                                        it.link
                                    }
                                },
                            )
                        )
                    }

                    //分享
                    state.link = "https://www.cngal.org/entries/index/${model.id}"
                    state.shareText =
                        "【词条】 ${model.name} | CnGal资料站 https://www.cngal.org/entries/index/${model.id}"

                    //发行列表
                    state.releases.clear()
                    model.releases.forEach {
                        val image = when (it.publishPlatformType)
                        {
                            PublishPlatformType.Steam -> "Steam.png"
                            PublishPlatformType.AppStore -> "AppStore.png"
                            PublishPlatformType.DLsite -> "DLsite.png"
                            PublishPlatformType.Epic -> "Epic.png"
                            PublishPlatformType.NS -> "Nintendo_Switch.png"
                            PublishPlatformType.GooglePlay -> "GooglePlay.png"
                            PublishPlatformType.TapTap -> "TapTap.png"
                            else ->
                            {
                                val name = it.publishPlatformName?.lowercase()
                                if (name != null && (name.contains("bilibili") || name.contains("b站") || name.contains(
                                        "哔哩哔哩"
                                    ))
                                )
                                {
                                    "bilibili.png"
                                }
                                else
                                {
                                    null
                                }

                            }
                        }

                        state.releases.add(
                            ReleaseUiState(
                                image = if (!image.isNullOrBlank())
                                {
                                    "https://res.cngal.org/_content/CnGalWebSite.Shared/images/${image}"
                                }
                                else
                                {
                                    null
                                },
                                value = if (it.timeNote.isNullOrBlank())
                                {
                                    if (!it.time.isNullOrBlank())
                                    {
                                        it.time.toDate().toString("yyyy年M月d日")
                                    }
                                    else
                                    {
                                        null
                                    }
                                }
                                else
                                {
                                    it.timeNote
                                },
                                id = 0,
                                name = if (it.publishPlatformType == PublishPlatformType.Other)
                                {
                                    it.publishPlatformName ?: "其他"
                                }
                                else
                                {
                                    it.publishPlatformType.toString()
                                },
                                link = when (it.publishPlatformType)
                                {
                                    PublishPlatformType.Steam -> if (it.link?.toIntOrNull() != null)
                                    {
                                        "https://store.steampowered.com/app/" + it.link
                                    }
                                    else
                                    {
                                        it.link
                                    }

                                    PublishPlatformType.AppStore -> "https://apps.apple.com/cn/app/" + it.link
                                    PublishPlatformType.GooglePlay -> "https://play.google.com/store/apps/details?id=" + it.link
                                    PublishPlatformType.Epic -> "https://store.epicgames.com/zh-CN/p/" + it.link
                                    PublishPlatformType.TapTap -> "https://www.taptap.cn/app/" + it.link
                                    PublishPlatformType.NS -> "https://ec.nintendo.com/HK/zh/titles/" + it.link
                                    PublishPlatformType.DLsite -> "https://www.dlsite.com/maniax/work/=/product_id/${it.link}.html"
                                    else ->
                                        it.link
                                },
                                type = it.type
                            )
                        )
                    }

                    state
                }

                _entry.update {
                    ApiResponse.success(model)
                }
            }
        }
    }
}