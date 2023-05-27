package com.cngal.app.model.home


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DiscountGameModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String,
    @Json(name = "cut")
    val cut: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "url")
    val url: String
)