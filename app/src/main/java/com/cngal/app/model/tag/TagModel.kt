package com.cngal.app.model.tag


import com.cngal.app.model.edit.EditState
import com.cngal.app.model.entry.EntryCardModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TagModel(
    @Json(name = "backgroundPicture")
    val backgroundPicture: String?,
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "childEntriesState")
    val childEntriesState: EditState,
    @Json(name = "childTagsState")
    val childTagsState: EditState,
    @Json(name = "childrenEntries")
    val childrenEntries: List<EntryCardModel>,
    @Json(name = "childrenTags")
    val childrenTags: List<TagCardModel>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "isEdit")
    val isEdit: Boolean,
    @Json(name = "isHidden")
    val isHidden: Boolean,
    @Json(name = "mainPicture")
    val mainPicture: String,
    @Json(name = "mainState")
    val mainState: EditState,
    @Json(name = "name")
    val name: String,
    @Json(name = "parentTag")
    val parentTag: TagCardModel?,
    @Json(name = "readerCount")
    val readerCount: Int,
    @Json(name = "smallBackgroundPicture")
    val smallBackgroundPicture: String?,
    @Json(name = "taglevels")
    val tagLevels: List<TagLevelModel>,
    @Json(name = "thumbnail")
    val thumbnail: String?
)