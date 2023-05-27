package com.cngal.app.service

import com.cngal.app.model.explore.EvaluationModel
import com.cngal.app.model.explore.PersonalRecommendModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ExploreService
{
    @GET("/api/home/ListEvaluations")
    suspend fun getEvaluationData(): List<EvaluationModel>

    @Headers("Content-Type: application/json")
    @POST("/api/home/GetPersonalizedRecommendations")
    suspend fun getPersonalizedRecommendations(@Body ids: List<Int>):  List<PersonalRecommendModel>

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()) .build()

        val instance: ExploreService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(ExploreService::class.java)
        }
    }
}