package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookingGoalModel(
    @Json(name = "name")
    val name: String,
    @Json(name = "target")
    val target: Int
)