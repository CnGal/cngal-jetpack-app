package com.cngal.app.model.article


import com.cngal.app.model.edit.EditState
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.OutlinkModel
import com.cngal.app.model.user.UserInforModel
import com.cngal.app.model.video.VideoCardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleModel(
    @Json(name = "authority")
    val authority: Boolean,
    @Json(name = "backgroundPicture")
    val backgroundPicture: String?,
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "canComment")
    val canComment: Boolean,
    @Json(name = "commentCount")
    val commentCount: Int,
    @Json(name = "createTime")
    val createTime: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "isEdit")
    val isEdit: Boolean,
    @Json(name = "isHidden")
    val isHidden: Boolean,
    @Json(name = "isThumbsUp")
    val isThumbsUp: Boolean,
    @Json(name = "lastEditTime")
    val lastEditTime: String,
    @Json(name = "mainPage")
    val mainPage: String,
    @Json(name = "mainPageState")
    val mainPageState: EditState,
    @Json(name = "mainPicture")
    val mainPicture: String?,
    @Json(name = "mainState")
    val mainState: EditState,
    @Json(name = "name")
    val name: String,
    @Json(name = "originalAuthor")
    val originalAuthor: String?,
    @Json(name = "originalLink")
    val originalLink: String?,
    @Json(name = "pubishTime")
    val publishTime: String?,
    @Json(name = "readerCount")
    val readerCount: Int,
    @Json(name = "relatedArticles")
    val relatedArticles: List<ArticleCardModel>,
    @Json(name = "relatedEntries")
    val relatedEntries: List<EntryCardModel>,
    @Json(name = "relatedOutlinks")
    val relatedOutlinks: List<OutlinkModel>,
    @Json(name = "relatedVideos")
    val relatedVideos: List<VideoCardModel>,
    @Json(name = "relevancesState")
    val relevancesState: EditState,
    @Json(name = "smallBackgroundPicture")
    val smallBackgroundPicture: String?,
    @Json(name = "thumbsUpCount")
    val thumbsUpCount: Int,
    @Json(name = "type")
    val type: ArticleType,
    @Json(name = "userInfor")
    val userInfor: UserInforModel
)