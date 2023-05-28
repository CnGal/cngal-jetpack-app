package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookingModel(
    @Json(name = "bookingCount")
    val bookingCount: Int,
    @Json(name = "goals")
    val goals: List<BookingGoalModel>,
    @Json(name = "open")
    val isOpen: Boolean
)