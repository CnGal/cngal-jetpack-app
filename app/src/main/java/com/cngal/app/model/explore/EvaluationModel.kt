package com.cngal.app.model.explore


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EvaluationModel(
    @Json(name = "articles")
    val articles: List<EvaluationArticleModel>,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String
)