package com.cngal.app.service

import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.NewsModel
import com.cngal.app.model.home.UpcomingGameModel
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface HomeService
{

    @GET("/api/home/GetHomeCarouselsView")
    suspend fun getCarouselData(): List<CarouselModel>

    @GET("/api/home/GetHomeNewsView")
    suspend fun getNewsData(): List<NewsModel>

    @GET("/api/news/GetWeeklyNewsOverview")
    suspend fun getWeeklyNewsData(): List<ArticleCardModel>

    @GET("/api/home/ListUpcomingGames")
    suspend fun getUpcomingGameData(): List<UpcomingGameModel>

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()) .build()

        val instance: HomeService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(HomeService::class.java)
        }
    }

}
