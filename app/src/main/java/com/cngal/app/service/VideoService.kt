package com.cngal.app.service

import com.cngal.app.model.tag.TagModel
import com.cngal.app.model.video.VideoModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface VideoService
{
    @GET("/api/videos/GetView/{id}")
    suspend fun getVideoData(@Path("id") id: Long): VideoModel

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val instance: VideoService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(VideoService::class.java)
        }
    }
}