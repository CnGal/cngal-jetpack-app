package com.cngal.app.viewmodel.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.article.ArticleModel
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.ArticleRepository
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

                _article.update {
                    ApiResponse.success(model)
                }
            }
        }
    }

}