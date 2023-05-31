package com.cngal.app.model.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchModel(
    @Json(name = "pagedResultDto")
    val pagedResultDto: PagedResultDto<SearchResultModel>
)