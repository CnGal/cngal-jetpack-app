package com.cngal.app.repository

import com.cngal.app.model.explore.EvaluationModel
import com.cngal.app.model.explore.PersonalRecommendModel
import com.cngal.app.service.ExploreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ExploreRepository
{
    companion object
    {
        fun getEvaluationData(): Flow<List<EvaluationModel>> = flow {
            val data = ExploreService.instance.getEvaluationData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getPersonalizedRecommendations(ids: List<Int>): Flow<List<PersonalRecommendModel>> =
            flow {
                val data = ExploreService.instance.getPersonalizedRecommendations(ids)
                emit(data)
            }.flowOn(Dispatchers.IO)
    }
}