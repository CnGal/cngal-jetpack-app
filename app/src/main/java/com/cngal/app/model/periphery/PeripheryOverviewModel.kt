package com.cngal.app.model.periphery


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeripheryOverviewModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "image")
    val image: String,
    @Json(name = "isThumbnail")
    val isThumbnail: Boolean,
    @Json(name = "name")
    val name: String,
    @Json(name = "objectId")
    val objectId: String?,
    @Json(name = "peripheries")
    val peripheries: List<PeripheryOverviewItemModel>,
    @Json(name = "type")
    val type: PeripheryOverviewType
)