package com.cngal.app.model.shared


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Duration(
    @Json(name = "days")
    val days: Int,
    @Json(name = "hours")
    val hours: Int,
    @Json(name = "microseconds")
    val microseconds: Int,
    @Json(name = "milliseconds")
    val milliseconds: Int,
    @Json(name = "minutes")
    val minutes: Int,
    @Json(name = "nanoseconds")
    val nanoseconds: Int,
    @Json(name = "seconds")
    val seconds: Int,
    @Json(name = "ticks")
    val ticks: Int,
    @Json(name = "totalDays")
    val totalDays: Int,
    @Json(name = "totalHours")
    val totalHours: Int,
    @Json(name = "totalMicroseconds")
    val totalMicroseconds: Int,
    @Json(name = "totalMilliseconds")
    val totalMilliseconds: Int,
    @Json(name = "totalMinutes")
    val totalMinutes: Int,
    @Json(name = "totalNanoseconds")
    val totalNanoseconds: Int,
    @Json(name = "totalSeconds")
    val totalSeconds: Int
)