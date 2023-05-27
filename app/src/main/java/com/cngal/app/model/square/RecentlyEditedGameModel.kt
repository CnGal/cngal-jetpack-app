package com.cngal.app.model.square


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RecentlyEditedGameModel(
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "publishTime")
    val publishTime: String,
    @Json(name = "url")
    val url: String
)