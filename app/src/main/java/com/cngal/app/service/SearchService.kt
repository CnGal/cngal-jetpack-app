package com.cngal.app.service

import com.cngal.app.model.search.SearchModel
import com.cngal.app.model.video.VideoModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService
{
    @GET("/api/home/Search")
    suspend fun getSearchData(
        @Query("text") text: String,
        @Query("types") types: List<String>,
        @Query("page") page: Int ,
    ): SearchModel

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val instance: SearchService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(SearchService::class.java)
        }
    }
}