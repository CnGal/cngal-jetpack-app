package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StaffPositionGroupModel(
    @Json(name = "modifier")
    val modifier: String,
    @Json(name = "names")
    val content: List<StaffModel>
)