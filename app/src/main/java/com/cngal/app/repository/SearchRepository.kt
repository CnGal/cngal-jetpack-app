package com.cngal.app.repository

import com.cngal.app.model.search.SearchModel
import com.cngal.app.model.video.VideoModel
import com.cngal.app.service.SearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchRepository
{
    companion object
    {
        fun getSearchData(text: String, types: List<String>, page: Int): Flow<SearchModel> = flow {
            val data = SearchService.instance.getSearchData(text,types,page)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}