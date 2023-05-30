package com.cngal.app.model.article


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleCardModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String,
    @Json(name = "commentCount")
    val commentCount: Int,
    @Json(name = "createUserId")
    val createUserId: String,
    @Json(name = "createUserName")
    val createUserName: String?,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "lastEditTime")
    val lastEditTime: String,
    @Json(name = "link")
    val link: String?,
    @Json(name = "mainImage")
    val mainImage: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "readerCount")
    val readerCount: Int,
    @Json(name = "thumbsUpCount")
    val thumbsUpCount: Int,
    @Json(name = "type")
    val type: ArticleType
)

