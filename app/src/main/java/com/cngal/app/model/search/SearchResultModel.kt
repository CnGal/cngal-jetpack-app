package com.cngal.app.model.search


import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.periphery.PeripheryCardModel
import com.cngal.app.model.tag.TagCardModel
import com.cngal.app.model.video.VideoCardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultModel(
    @Json(name = "article")
    val article: ArticleCardModel?,
    @Json(name = "entry")
    val entry: EntryCardModel?,
    @Json(name = "periphery")
    val periphery: PeripheryCardModel?,
    @Json(name = "tag")
    val tag: TagCardModel?,
    @Json(name = "video")
    val video: VideoCardModel?
)