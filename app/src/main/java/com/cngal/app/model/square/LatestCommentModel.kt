package com.cngal.app.model.square


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LatestCommentModel(
    @Json(name = "content")
    val content: String,
    @Json(name = "image")
    val image: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "time")
    val time: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "userImage")
    val userImage: String,
    @Json(name = "userName")
    val userName: String,
    @Json(name = "userId")
    val userId: String
)