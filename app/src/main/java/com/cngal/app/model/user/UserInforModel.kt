package com.cngal.app.model.user


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserInforModel(
    @Json(name = "articleCount")
    val articleCount: Int,
    @Json(name = "articleReadCount")
    val articleReadCount: Int,
    @Json(name = "backgroundImage")
    val backgroundImage: String?,
    @Json(name = "editCount")
    val editCount: Int,
    @Json(name = "id")
    val id: String,
    @Json(name = "integral")
    val integral: Int,
    @Json(name = "isSignIn")
    val isSignIn: Boolean,
    @Json(name = "mBgImage")
    val mBgImage: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "personalSignature")
    val personalSignature: String?,
    @Json(name = "photoPath")
    val photoPath: String,
    @Json(name = "ranks")
    val ranks: List<RankModel>,
    @Json(name = "sBgImage")
    val sBgImage: String?,
    @Json(name = "signInDays")
    val signInDays: Int,
    @Json(name = "videoCount")
    val videoCount: Int
)