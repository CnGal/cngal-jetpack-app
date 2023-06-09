package com.cngal.app.viewmodel.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.article.ArticleModel
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.ArticleRepository
import com.cngal.app.uistate.article.SingleArticleUiState
import com.cngal.app.uistate.tag.SingleTagUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SingleArticleViewModel : ViewModel()
{
    private val _article = MutableStateFlow(ApiResponse.empty<ArticleModel>())
    val article = _article.asStateFlow()

    private val _uiState = MutableStateFlow(SingleArticleUiState())
    val uiState = _uiState.asStateFlow()

    fun loadArticleData(id: Long?)
    {
        if (id == null || id <= 0)
        {
            return
        }

        viewModelScope.launch {
            ArticleRepository.getArticleData(id).onStart {
                _article.value = ApiResponse.loading()
            }.catch { e ->
                _article.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _uiState.update { state ->

                    //分享
                    state.link = "https://www.cngal.org/articles/index/${model.id}"
                    state.shareText =
                        "【文章】 ${model.name} | CnGal资料站 https://www.cngal.org/articles/index/${model.id}"

                    state
                }
                _article.update {
                    ApiResponse.success(model)
                }
            }
        }
    }

}