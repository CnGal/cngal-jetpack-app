package com.cngal.app.repository

import com.cngal.app.model.square.AnnouncementModel
import com.cngal.app.model.square.FriendLinkModel
import com.cngal.app.model.square.HotTagModel
import com.cngal.app.model.square.LatestCommentModel
import com.cngal.app.model.square.RecentlyEditedGameModel
import com.cngal.app.service.HomeService
import com.cngal.app.service.SquareService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SquareRepository
{
    companion object
    {
        fun getAnnouncementData(): Flow<List<AnnouncementModel>> = flow {
            val data = SquareService.instance.getAnnouncementData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getRecentlyEditedGameData(): Flow<List<RecentlyEditedGameModel>> = flow {
            val data = SquareService.instance.getRecentlyEditedGameData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getFriendLinkData(): Flow<List<FriendLinkModel>> = flow {
            val data = SquareService.instance.getFriendLinkData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getHotTagData(): Flow<List<HotTagModel>> = flow {
            val data = SquareService.instance.getHotTagData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getLatestCommentData(): Flow<List<LatestCommentModel>> = flow {
            val data = SquareService.instance.getLatestCommentData()
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}