package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AudioModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "duration")
    val duration: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "thumbnail")
    val thumbnail: String?,
    @Json(name = "url")
    val url: String
)