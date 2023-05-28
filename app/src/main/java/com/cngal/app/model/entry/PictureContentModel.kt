package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureContentModel(
    @Json(name = "note")
    val note: String?,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "url")
    val url: String
)