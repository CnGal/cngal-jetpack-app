package com.cngal.app.viewmodel.entry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.entry.EntryModel
import com.cngal.app.model.entry.EntryType
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

    fun loadEntryData(id: Int)
    {
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

                    state
                }

                _entry.update {
                    ApiResponse.success(model)
                }
            }
        }
    }
}