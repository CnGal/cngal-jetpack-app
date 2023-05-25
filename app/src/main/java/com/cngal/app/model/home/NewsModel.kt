package com.cngal.app.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.Date

@JsonClass(generateAdapter = true)
data class NewsModel(
    @Json(name = "articleId")
    val articleId: Int,
    @Json(name = "groupId")
    val groupId: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "text")
    val text: String,
    @Json(name = "time")
    val time: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "userId")
    val userId: String?
)