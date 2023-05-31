package com.cngal.app.model.entry


import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.edit.EditState
import com.cngal.app.model.shared.PictureModel
import com.cngal.app.model.video.VideoCardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EntryModel(
    @Json(name = "anotherName")
    val anotherName: String?,
    @Json(name = "articleRelevances")
    val articleRelevances: List<ArticleCardModel>,
    @Json(name = "audio")
    val audio: List<AudioModel>,
    @Json(name = "audioState")
    val audioState: EditState,
    @Json(name = "backgroundPicture")
    val backgroundPicture: String?,
    @Json(name = "booking")
    val booking: BookingModel?,
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "canComment")
    val canComment: Boolean,
    @Json(name = "entryRelevances")
    val entryRelevances: List<EntryCardModel>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imagesState")
    val imagesState: EditState,
    @Json(name = "inforState")
    val inforState: EditState,
    @Json(name = "information")
    val information: List<InformationModel>,
    @Json(name = "isDubbing")
    val isDubbing: Boolean,
    @Json(name = "isEdit")
    val isEdit: Boolean,
    @Json(name = "isHidden")
    val isHidden: Boolean,
    @Json(name = "isHideOutlink")
    val isHideOutlink: Boolean,
    @Json(name = "isScored")
    val isScored: Boolean,
    @Json(name = "mainPage")
    val mainPage: String?,
    @Json(name = "mainPageState")
    val mainPageState: EditState,
    @Json(name = "mainPicture")
    val mainPicture: String?,
    @Json(name = "mainState")
    val mainState: EditState,
    @Json(name = "name")
    val name: String,
    @Json(name = "newsOfEntry")
    val newsOfEntry: List<NewsModel>,
    @Json(name = "otherRelevances")
    val otherRelevances: List<OutlinkModel>,
    @Json(name = "pictures")
    val pictures: List<PictureModel>,
    @Json(name = "productionGroups")
    val productionGroups: List<StaffModel>,
    @Json(name = "publishers")
    val publishers: List<StaffModel>,
    @Json(name = "releases")
    val releases: List<ReleaseModel>,
    @Json(name = "relevancesState")
    val relevancesState: EditState,
    @Json(name = "roles")
    val roles: List<RoleCardModel>,
    @Json(name = "smallBackgroundPicture")
    val smallBackgroundPicture: String?,
    @Json(name = "staffGames")
    val staffGames: List<EntryCardModel>,
    @Json(name = "staffs")
    val staffs: List<StaffGroupModel>,
    @Json(name = "tagState")
    val tagState: EditState,
    @Json(name = "tags")
    val tags: List<TagModel>,
    @Json(name = "template")
    val template: EntryStyleTemplate,
    @Json(name = "thumbnail")
    val thumbnail: String?,
    @Json(name = "type")
    val type: EntryType,
    @Json(name = "videoRelevances")
    val videoRelevances: List<VideoCardModel>,
    @Json(name = "websiteAddInfor")
    val websiteAddInfor: WebsiteAddInforModel?,
    @Json(name = "websiteState")
    val websiteState: EditState
)