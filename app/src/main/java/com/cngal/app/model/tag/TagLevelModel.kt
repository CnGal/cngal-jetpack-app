package com.cngal.app.model.tag


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagLevelModel(
    @Json(name = "key")
    val key: String,
    @Json(name = "value")
    val value: Int
)