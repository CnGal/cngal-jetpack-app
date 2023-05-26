package com.cngal.app.model.explore


import com.cngal.app.model.article.ArticleType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EvaluationArticleModel(
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "originalAuthor")
    val originalAuthor: String,
    @Json(name = "type")
    val type: ArticleType
)