package com.cngal.app.model.periphery


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeripheryOverviewItemModel(
    @Json(name = "collectedCount")
    val collectedCount: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "isCollected")
    val isCollected: Boolean,
    @Json(name = "name")
    val name: String
)