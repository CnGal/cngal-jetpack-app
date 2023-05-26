package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreInforModel(
    @Json(name = "currencyCode")
    val currencyCode: CurrencyCode,
    @Json(name = "cutLowest")
    val cutLowest: Double?,
    @Json(name = "cutNow")
    val cutNow: Double?,
    @Json(name = "estimationOwnersMax")
    val estimationOwnersMax: Int?,
    @Json(name = "estimationOwnersMin")
    val estimationOwnersMin: Int?,
    @Json(name = "evaluationCount")
    val evaluationCount: Int?,
    @Json(name = "link")
    val link: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "originalPrice")
    val originalPrice: Double?,
    @Json(name = "platformName")
    val platformName: String?,
    @Json(name = "platformType")
    val platformType: PublishPlatformType,
    @Json(name = "playTime")
    val playTime: Int?,
    @Json(name = "priceLowest")
    val priceLowest: Double?,
    @Json(name = "priceNow")
    val priceNow: Double?,
    @Json(name = "recommendationRate")
    val recommendationRate: Double?,
    @Json(name = "state")
    val state: StoreState,
    @Json(name = "updateTime")
    val updateTime: String,
    @Json(name = "updateType")
    val updateType: StoreUpdateType
)

enum class PublishPlatformType
{
    Steam,
    TapTap,
    Epic,
    AppStore,
    GooglePlay,
    NS,
    DLsite,
    Other,
}

enum class StoreState
{
    None,
    NotPublished,
    OnSale,
    Takedown
}

enum class CurrencyCode {
    CNY,
    JPY,
    USD,
    HKD,
    TWD,
}
enum class StoreUpdateType
{
    Automatic,
    Manual,
}
