package com.cngal.app.repository

import com.cngal.app.model.article.ArticleModel
import com.cngal.app.model.periphery.PeripheryModel
import com.cngal.app.service.ArticleService
import com.cngal.app.service.PeripheryService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PeripheryRepository
{
    companion object
    {
        fun getPeripheryData(id: Long): Flow<PeripheryModel> = flow {
            val data = PeripheryService.instance.getPeripheryData(id)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}