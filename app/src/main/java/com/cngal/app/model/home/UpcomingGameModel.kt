package com.cngal.app.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpcomingGameModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "publishTime")
    val publishTime: String,
    @Json(name = "url")
    val url: String
)