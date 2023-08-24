package com.cngal.app.viewmodel.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.extension.toDate
import com.cngal.app.extension.toString
import com.cngal.app.model.entry.OutlinkModel
import com.cngal.app.model.periphery.InformationContentModel
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.model.video.VideoModel
import com.cngal.app.repository.VideoRepository
import com.cngal.app.uistate.video.SingleVideoUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingleVideoViewModel : ViewModel()
{
    private val _video = MutableStateFlow(ApiResponse.empty<VideoModel>())
    val video = _video.asStateFlow()

    private val _uiState = MutableStateFlow(SingleVideoUiState())
    val uiState = _uiState.asStateFlow()

    fun loadVideoData(id: Long?)
    {
        if (id == null || id <= 0)
        {
            return
        }

        viewModelScope.launch {
            VideoRepository.getVideoData(id).onStart {
                _video.value = ApiResponse.loading()
            }.catch { e ->
                _video.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _uiState.update { state ->

                    //标签
                    state.tags.clear()
                    if (!model.type.isNullOrBlank())
                    {
                        state.tags.add(model.type)
                    }
                    if (model.isInteractive)
                    {
                        state.tags.add("互动视频")
                    }
                    state.tags.add(model.copyright.toString())

                    //基础信息
                    state.information.clear()
                    if (model.duration != "00:00:00")
                    {
                        state.information.add(InformationContentModel("时长", model.duration))
                    }

                    if (!model.originalAuthor.isNullOrBlank())
                    {
                        state.information.add(
                            InformationContentModel(
                                "原作者",
                                model.originalAuthor
                            )
                        )
                    }
                    state.information.add(
                        InformationContentModel(
                            "发布时间",
                            model.publishTime.toDate().toString("yyyy-MM-dd HH:mm")
                        )
                    )
                    state.information.add(
                        InformationContentModel(
                            "最后编辑",
                            model.lastEditTime.toDate().toString("yyyy-MM-dd HH:mm")
                        )
                    )
                    //外部链接
                    model.relatedOutlinks.forEach {
                        val image = when (it.displayName)
                        {
                            "bilibili" -> "bilibili.png"
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
                                displayValue = it.displayValue,
                                id = 0,
                                displayName = when (it.displayName)
                                {
                                    "bilibili" -> "前往哔哩哔哩观看"
                                    else ->
                                    {
                                        it.displayName
                                    }
                                },
                                link = it.link,
                            )
                        )
                    }

                    //分享
                    state.link = "https://www.cngal.org/videos/index/${model.id}"
                    state.shareText =
                        "【视频】 ${model.name} | CnGal资料站 https://www.cngal.org/videos/index/${model.id}"

                    state
                }

                _video.update {
                    ApiResponse.success(model)
                }
            }
        }
    }
}