package com.cngal.app.model.tag


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagCardModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lastEditTime")
    val lastEditTime: String,
    @Json(name = "mainImage")
    val mainImage: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "readerCount")
    val readerCount: Int
)