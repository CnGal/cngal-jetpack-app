package com.cngal.app.repository

import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.home.AnnouncementModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.FriendLinkModel
import com.cngal.app.model.home.LatestArticleModel
import com.cngal.app.model.home.LatestVideoModel
import com.cngal.app.model.home.NewsModel
import com.cngal.app.model.home.PublishedGameModel
import com.cngal.app.model.home.RecentlyEditedGameModel
import com.cngal.app.model.home.UpcomingGameModel
import com.cngal.app.service.HomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepository
{
    companion object
    {
         fun getCarouselData( ): Flow<List<CarouselModel>> = flow {
            val data = HomeService.instance.getCarouselData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getNewsData( ): Flow<List<NewsModel>> = flow {
            val data = HomeService.instance.getNewsData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getWeeklyNewsData( ): Flow<List<ArticleCardModel>> = flow {
            val data = HomeService.instance.getWeeklyNewsData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getUpcomingGameData( ): Flow<List<UpcomingGameModel>> = flow {
            val data = HomeService.instance.getUpcomingGameData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getPublishedGameData( ): Flow<List<PublishedGameModel>> = flow {
            val data = HomeService.instance.getPublishedGameData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getLatestArticleData( ): Flow<List<LatestArticleModel>> = flow {
            val data = HomeService.instance.getLatestArticleData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getLatestVideoData( ): Flow<List<LatestVideoModel>> = flow {
            val data = HomeService.instance.getLatestVideoData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getAnnouncementData( ): Flow<List<AnnouncementModel>> = flow {
            val data = HomeService.instance.getAnnouncementData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getRecentlyEditedGameData( ): Flow<List<RecentlyEditedGameModel>> = flow {
            val data = HomeService.instance.getRecentlyEditedGameData()
            emit(data)
        }.flowOn(Dispatchers.IO)

        fun getFriendLinkData( ): Flow<List<FriendLinkModel>> = flow {
            val data = HomeService.instance.getFriendLinkData()
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}