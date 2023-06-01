package com.cngal.app.viewmodel.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.search.SearchResultModel
import com.cngal.app.repository.SearchRepository
import com.cngal.app.uistate.search.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel()
{
    private val _searchResults =
        MutableStateFlow(SnapshotStateList<List<SearchResultModel>>())
    val searchResults = _searchResults.asStateFlow()

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    var isCompleted by mutableStateOf(true)
        private set

    private var isLoading: Boolean = false

    var text by mutableStateOf("")
        private set

    init
    {
        search()
    }

    fun updateText(_text: String)
    {
        text = _text
    }


    fun search()
    {
        _searchResults.update {
            it.clear()
            it
        }
        _uiState.update {
            it.page = 0
            isCompleted = false
            it
        }

        loadMoreResults()
    }

    fun addType(type: String)
    {
        _uiState.update {
            it.types.add(type)
            it
        }
    }

    fun removeType(type: String)
    {
        _uiState.update {
            it.types.remove(type)
            it
        }
    }

    fun loadMoreResults()
    {
        if (isCompleted || isLoading)
        {
            return
        }


        isLoading = true

        viewModelScope.launch {
            SearchRepository.getSearchData(
                text,
                _uiState.value.types,
                _uiState.value.page + 1
            )
                .onStart {
                    isLoading = true

                }.catch { e ->
                    isLoading = false

                }.collect { response ->
                    run {
                        _uiState.update {
                            isLoading = false
                            it.page = response.pagedResultDto.currentPage
                            it.totalPage = response.pagedResultDto.totalPages
                            if (it.page == it.totalPage || it.totalPage == 0)
                            {
                                isCompleted = true
                            }
                            it
                        }
                        _searchResults.update {
                            if (response.pagedResultDto.currentPage != response.pagedResultDto.totalPages || response.pagedResultDto.totalPages == 0)
                            {
                                it.addAll(response.pagedResultDto.data.chunked(2))
                            }
                            it
                        }

                    }
                }
        }
    }

}