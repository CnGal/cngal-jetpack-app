package com.cngal.app.repository

import com.cngal.app.model.entry.EntryModel
import com.cngal.app.model.explore.EvaluationModel
import com.cngal.app.model.explore.PersonalRecommendModel
import com.cngal.app.model.periphery.PeripheryOverviewModel
import com.cngal.app.service.EntryService
import com.cngal.app.service.ExploreService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EntryRepository
{
    companion object
    {
        fun getEntryData(id:Int): Flow<EntryModel> = flow {
            val data = EntryService.instance.getEntryData(id)
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getPeripheryOverviewData(id:Int): Flow<PeripheryOverviewModel> = flow {
            val data = EntryService.instance.getPeripheryOverviewData(id)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}