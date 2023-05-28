package com.cngal.app.viewmodel.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.uistate.explore.ExploreUiState
import com.cngal.app.model.explore.EvaluationModel
import com.cngal.app.model.explore.PersonalRecommendModel
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.ExploreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExploreViewModel : ViewModel()
{
    private val _evaluations = MutableStateFlow(ApiResponse.empty<List<EvaluationModel>>())
    val evaluations = _evaluations.asStateFlow()

    private val _personalRecommends =
        MutableStateFlow(SnapshotStateList<PersonalRecommendModel>())
    val personalRecommends = _personalRecommends.asStateFlow()

    private val _uiState = MutableStateFlow(ExploreUiState())
    val uiState = _uiState.asStateFlow()

    init
    {
        getEvaluationData()
    }

    fun refreshRecommends()
    {
        _personalRecommends.update {
            it.clear()
            it
        }
    }

    fun loadMoreRecommends()
    {
        viewModelScope.launch {
            ExploreRepository.getPersonalizedRecommendations(_personalRecommends.value.map { it.id })
                .onStart {
                    _uiState.update {
                        it.isLoading = true
                        it
                    }
                }.catch { e ->
                    _uiState.update {
                        it.isLoading = false
                        it
                    }
                }.collect { response ->
                    run {
                        _uiState.update {
                            it.isLoading = false
                            if (response.isEmpty())
                            {
                                it.isCompleted = true
                            }
                            it
                        }
                        _personalRecommends.update {
                            if (response.isNotEmpty())
                            {
                                it.addAll(response)
                            }
                            it
                        }

                    }
                }
        }
    }

    private fun getEvaluationData()
    {
        viewModelScope.launch {
            ExploreRepository.getEvaluationData().onStart {
                _evaluations.value = ApiResponse.loading()
            }.catch { e ->
                _evaluations.value =
                    ApiResponse.error(e)
            }.collect { model ->

                model.forEach(){
                    it.articles=it.articles.shuffled().take(2)
                }

                _evaluations.value =
                    ApiResponse.success(model.shuffled().take(1))
            }
        }
    }
}