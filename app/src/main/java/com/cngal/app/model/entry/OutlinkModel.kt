package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OutlinkModel(
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "displayValue")
    val displayValue: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String?,
    @Json(name = "link")
    val link: String?
)