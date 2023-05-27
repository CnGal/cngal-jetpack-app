package com.cngal.app.model.square


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnouncementModel(
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "url")
    val url: String,
    @Json(name = "briefIntroduction")
    val briefIntroduction: String,
)