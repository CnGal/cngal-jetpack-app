package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)