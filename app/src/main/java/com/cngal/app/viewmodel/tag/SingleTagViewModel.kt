package com.cngal.app.viewmodel.tag

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.model.tag.TagModel
import com.cngal.app.repository.TagRepository
import com.cngal.app.uistate.tag.SingleTagUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingleTagViewModel : ViewModel()
{
    private val _tag = MutableStateFlow(ApiResponse.empty<TagModel>())
    val tag = _tag.asStateFlow()

    private val _uiState = MutableStateFlow(SingleTagUiState())
    val uiState = _uiState.asStateFlow()

    fun loadTagData(id: Int?)
    {
        if (id == null || id <= 0)
        {
            return
        }

        viewModelScope.launch {
            TagRepository.getTagData(id).onStart {
                _tag.value = ApiResponse.loading()
            }.catch { e ->
                _tag.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _uiState.update { state ->
                    //父标签
                    state.parentTags = model.tagLevels.dropLast(1).toList()

                    //分享
                    state.link = "https://www.cngal.org/tags/index/${model.id}"
                    state.shareText =
                        "【标签】 ${model.name} | CnGal资料站 https://www.cngal.org/tags/index/${model.id}"

                    state
                }

                _tag.update {
                    ApiResponse.success(model)
                }
            }
        }
    }
}