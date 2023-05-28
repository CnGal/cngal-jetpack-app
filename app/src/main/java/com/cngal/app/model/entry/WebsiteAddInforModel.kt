package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WebsiteAddInforModel(
    @Json(name = "color")
    val color: String?,
    @Json(name = "firstPage")
    val firstPage: String?,
    @Json(name = "html")
    val html: String?,
    @Json(name = "images")
    val images: List<WebsiteAddInforImageModel>,
    @Json(name = "impressions")
    val impressions: String?,
    @Json(name = "introduction")
    val introduction: String?,
    @Json(name = "logo")
    val logo: String?,
    @Json(name = "subTitle")
    val subTitle: String?
)