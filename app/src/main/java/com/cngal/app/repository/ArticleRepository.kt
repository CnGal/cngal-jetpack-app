package com.cngal.app.repository

import android.util.Log
import com.cngal.app.model.article.ArticleModel
import com.cngal.app.model.entry.EntryModel
import com.cngal.app.service.ArticleService
import com.cngal.app.service.EntryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ArticleRepository
{
    companion object
    {
        fun getArticleData(id: Long): Flow<ArticleModel> = flow {
            val data = ArticleService.instance.getArticleData(id)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}