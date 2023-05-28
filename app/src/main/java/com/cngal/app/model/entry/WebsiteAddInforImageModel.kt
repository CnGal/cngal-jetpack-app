package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WebsiteAddInforImageModel(
    @Json(name = "note")
    val note: String?,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "size")
    val size: EntryWebsiteImageSize,
    @Json(name = "type")
    val type: EntryWebsiteImageType,
    @Json(name = "url")
    val url: String
)

 enum class EntryWebsiteImageSize
{
    Large,
    Small
}

 enum class EntryWebsiteImageType
{
    Carousel,
    Background
}