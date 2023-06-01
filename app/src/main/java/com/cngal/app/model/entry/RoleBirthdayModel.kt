package com.cngal.app.model.entry

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class RoleBirthdayModel
    (
    @Json(name = "addInfors")
    val addInfors: List<EntryCardAddInforModel>,

    @Json(name = "audio")
    val audio: List<AudioModel>,

    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,

    @Json(name = "commentCount")
    val commentCount: Int,

    @Json(name = "id")
    val id: Int,

    @Json(name = "lastEditTime")
    val lastEditTime: String,

    @Json(name = "mainImage")
    val mainImage: String,

    @Json(name = "name")
    val name: String,

    @Json(name = "publishTime")
    val publishTime: String?,

    @Json(name = "readerCount")
    val readerCount: Int,

    @Json(name = "type")
    val type: EntryType,

    @Json(name = "brithday")
    val birthday: String,
)