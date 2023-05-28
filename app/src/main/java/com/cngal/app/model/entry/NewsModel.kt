package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsModel(
    @Json(name = "articleId")
    val articleId: Int,
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "groupId")
    val groupId: Int,
    @Json(name = "groupName")
    val groupName: String,
    @Json(name = "happenedTime")
    val happenedTime: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "link")
    val link: String?,
    @Json(name = "newsType")
    val newsType: String?,
    @Json(name = "title")
    val title: String,
    @Json(name = "userId")
    val userId: String?
)