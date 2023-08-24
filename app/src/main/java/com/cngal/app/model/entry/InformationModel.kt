package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InformationModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "value")
    val value: String,
    @Json(name = "icon")
    val icon: String
)