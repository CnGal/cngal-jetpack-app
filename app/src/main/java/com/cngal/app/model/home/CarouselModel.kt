package com.cngal.app.model.home

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CarouselModel (
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "note")
    val note: String,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "type")
    val type: CarouselType
)

enum class CarouselType {
    Home,
    ThematicPage,
}