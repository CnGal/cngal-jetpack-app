package com.cngal.app.service

import com.cngal.app.model.entry.EntryModel
import com.cngal.app.model.periphery.PeripheryOverviewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EntryService
{
    @GET("/api/entries/GetEntryView/{id}")
    suspend fun getEntryData(
        @Path("id") id: Int,
        @Query("renderMarkdown") renderMarkdown: Boolean = false
    ): EntryModel

    @GET("/api/peripheries/GetEntryOverviewPeripheries/{id}")
    suspend fun getPeripheryOverviewData(@Path("id") id: Int): PeripheryOverviewModel

    companion object
    {
        private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        val instance: EntryService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.cngal.org/")
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
            retrofit.create(EntryService::class.java)
        }
    }
}