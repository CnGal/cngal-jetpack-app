package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntryCardAddInforModel(
    @Json(name = "contents")
    val contents: List<EntryCardAddInforContentModel>,
    @Json(name = "modifier")
    val modifier: String
)