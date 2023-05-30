package com.cngal.app.model.user


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RankModel(
    @Json(name = "css")
    val css: String?,
    @Json(name = "image")
    val image: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "styles")
    val styles: String?,
    @Json(name = "text")
    val text: String?,
    @Json(name = "type")
    val type: RankType
)

