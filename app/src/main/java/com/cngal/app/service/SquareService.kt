package com.cngal.app.service

import com.cngal.app.model.square.AnnouncementModel
import com.cngal.app.model.square.FriendLinkModel
import com.cngal.app.model.square.HotTagModel
import com.cngal.app.model.square.LatestCommentModel
import com.cngal.app.model.square.RecentlyEditedGameModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface SquareService
{

    @GET("/api/home/ListAnnouncements")
    suspend fun getAnnouncementData(): List<AnnouncementModel>

    @GET("/api/home/ListRecentlyEditedGames")
    suspend fun getRecentlyEditedGameData(): List<RecentlyEditedGameModel>

    @GET("/api/home/ListFriendLinks")
    suspend fun getFriendLinkData(): List<FriendLinkModel>

    @GET("/api/home/ListHotTags")
    suspend fun getHotTagData(): List<HotTagModel>

    @GET("/api/home/ListLatestComments")
    suspend fun getLatestCommentData(): List<LatestCommentModel>

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()) .build()

        val instance: SquareService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(SquareService::class.java)
        }
    }
}