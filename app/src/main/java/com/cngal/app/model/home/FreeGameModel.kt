package com.cngal.app.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FreeGameModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "tags")
    var tags: List<String>,
    @Json(name = "url")
    val url: String
)