package com.cngal.app.model.search


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagedResultDto<TModel>(
    @Json(name = "currentPage")
    val currentPage: Int,
    @Json(name = "data")
    val `data`: List<TModel>,
    @Json(name = "filterText")
    val filterText: String?,
    @Json(name = "maxResultCount")
    val maxResultCount: Int,
    @Json(name = "screeningConditions")
    val screeningConditions: String?,
    @Json(name = "showFirst")
    val showFirst: Boolean,
    @Json(name = "showLast")
    val showLast: Boolean,
    @Json(name = "showNext")
    val showNext: Boolean,
    @Json(name = "showPrevious")
    val showPrevious: Boolean,
    @Json(name = "sorting")
    val sorting: String?,
    @Json(name = "totalCount")
    val totalCount: Int,
    @Json(name = "totalPages")
    val totalPages: Int
)