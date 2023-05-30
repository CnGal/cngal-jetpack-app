package com.cngal.app.repository

import com.cngal.app.model.article.ArticleModel
import com.cngal.app.model.tag.TagModel
import com.cngal.app.service.ArticleService
import com.cngal.app.service.TagService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class TagRepository
{
    companion object
    {
        fun getTagData(id: Int): Flow<TagModel> = flow {
            val data = TagService.instance.getTagData(id)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}