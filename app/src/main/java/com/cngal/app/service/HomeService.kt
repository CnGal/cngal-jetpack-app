package com.cngal.app.service

import androidx.annotation.Keep
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.entry.RoleBirthdayModel
import com.cngal.app.model.square.AnnouncementModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.DiscountGameModel
import com.cngal.app.model.home.FreeGameModel
import com.cngal.app.model.square.FriendLinkModel
import com.cngal.app.model.home.LatestArticleModel
import com.cngal.app.model.home.LatestVideoModel
import com.cngal.app.model.home.NewsModel
import com.cngal.app.model.home.PublishedGameModel
import com.cngal.app.model.square.RecentlyEditedGameModel
import com.cngal.app.model.home.UpcomingGameModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@Keep
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

    @GET("/api/home/ListPublishedGames")
    suspend fun getPublishedGameData(): List<PublishedGameModel>

    @GET("/api/home/ListLatestArticles")
    suspend fun getLatestArticleData(): List<LatestArticleModel>

    @GET("/api/home/ListLatestVideos")
    suspend fun getLatestVideoData(): List<LatestVideoModel>

    @GET("/api/home/ListFreeGames")
    suspend fun getFreeGameData(): List<FreeGameModel>

    @GET("/api/home/ListDiscountGames")
    suspend fun getDiscountGameData(): List<DiscountGameModel>

    @GET("/api/entries/GetRoleBirthdaysByTime")
    suspend fun getRoleBirthdayData(@Query("month") month: Int,@Query("day") day: Int): List<RoleBirthdayModel>

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
