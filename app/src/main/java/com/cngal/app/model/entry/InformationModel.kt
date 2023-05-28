package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class InformationModel(
    @Json(name = "informations")
    val content: List<InformationContentModel>,
    @Json(name = "modifier")
    val modifier: String
)