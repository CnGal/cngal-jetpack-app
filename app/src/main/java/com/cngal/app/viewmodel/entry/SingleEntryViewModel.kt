package com.cngal.app.viewmodel.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.entry.EntryModel
import com.cngal.app.model.entry.EntryType
import com.cngal.app.model.entry.getGeneralType
import com.cngal.app.model.entry.PositionGeneralType
import com.cngal.app.model.entry.PublishPlatformType
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.EntryRepository
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

    fun loadEntryData(id: Int?)
    {
        if (id == null || id <= 0)
        {
            return
        }

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
                    state.news=model.newsOfEntry.take(3)

                    state
                }

                _entry.update {
                    ApiResponse.success(model)
                }
            }
        }
    }
}