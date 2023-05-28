package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StaffModel(
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "id")
    val id: Int
)