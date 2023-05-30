package com.cngal.app.service

import com.cngal.app.model.article.ArticleModel
import com.cngal.app.model.entry.EntryModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService
{
    @GET("/api/articles/GetArticleView/{id}")
    suspend fun getArticleData(@Path("id") id: Long): ArticleModel

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val instance: ArticleService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(ArticleService::class.java)
        }
    }
}