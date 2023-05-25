package com.cngal.app.repository

import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.NewsModel
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
    }
}