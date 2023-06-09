package com.cngal.app.model.shared


import com.cngal.app.model.entry.PictureContentModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PictureModel(
    @Json(name = "modifier")
    val modifier: String?,
    @Json(name = "pictures")
    val content: List<PictureContentModel>
)