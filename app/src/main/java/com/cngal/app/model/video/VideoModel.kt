package com.cngal.app.model.video


import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.edit.EditState
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.OutlinkModel
import com.cngal.app.model.shared.PictureModel
import com.cngal.app.model.user.UserInforModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoModel(
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
    @Json(name = "copyright")
    val copyright: CopyrightType,
    @Json(name = "createTime")
    val createTime: String,
    @Json(name = "displayName")
    val displayName: String,
    @Json(name = "duration")
    val duration: String,
    @Json(name = "id")
    val id: Long,
    @Json(name = "imagesState")
    val imagesState: EditState,
    @Json(name = "isCreatedByCurrentUser")
    val isCreatedByCurrentUser: Boolean,
    @Json(name = "isEdit")
    val isEdit: Boolean,
    @Json(name = "isHidden")
    val isHidden: Boolean,
    @Json(name = "isInteractive")
    val isInteractive: Boolean,
    @Json(name = "lastEditTime")
    val lastEditTime: String,
    @Json(name = "mainPage")
    val mainPage: String?,
    @Json(name = "mainPageState")
    val mainPageState: EditState,
    @Json(name = "mainPicture")
    val mainPicture: String,
    @Json(name = "mainState")
    val mainState: EditState,
    @Json(name = "name")
    val name: String,
    @Json(name = "originalAuthor")
    val originalAuthor: String?,
    @Json(name = "pictures")
    val pictures: List<PictureModel>,
    @Json(name = "priority")
    val priority: Int,
    @Json(name = "pubishTime")
    val publishTime: String,
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
    @Json(name = "type")
    val type: String?,
    @Json(name = "userInfor")
    val userInfor: UserInforModel
)