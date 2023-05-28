package com.cngal.app.model.video


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoCardModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "commentCount")
    val commentCount: Int,
    @Json(name = "createUserId")
    val createUserId: String,
    @Json(name = "createUserName")
    val createUserName: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "lastEditTime")
    val lastEditTime: String,
    @Json(name = "mainImage")
    val mainImage: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "pubishTime")
    val publishTime: String,
    @Json(name = "readerCount")
    val readerCount: Int,
    @Json(name = "type")
    val type: String
)