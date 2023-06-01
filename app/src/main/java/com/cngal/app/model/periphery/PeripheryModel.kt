package com.cngal.app.model.periphery


import com.cngal.app.model.edit.EditState
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.PictureContentModel
import com.cngal.app.model.shared.PictureModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PeripheryModel(
    @Json(name = "author")
    val author: String?,
    @Json(name = "backgroundPicture")
    val backgroundPicture: String?,
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "canComment")
    val canComment: Boolean,
    @Json(name = "category")
    val category: String?,
    @Json(name = "collectedCount")
    val collectedCount: Int,
    @Json(name = "commentCount")
    val commentCount: Int,
    @Json(name = "displayName")
    val displayName: String?,
    @Json(name = "entries")
    val entries: List<EntryCardModel>,
    @Json(name = "id")
    val id: Long,
    @Json(name = "imagesState")
    val imagesState: EditState,
    @Json(name = "individualParts")
    val individualParts: String?,
    @Json(name = "isAvailableItem")
    val isAvailableItem: Boolean,
    @Json(name = "isEdit")
    val isEdit: Boolean,
    @Json(name = "isHidden")
    val isHidden: Boolean,
    @Json(name = "isReprint")
    val isReprint: Boolean,
    @Json(name = "mainPicture")
    val mainPicture: String,
    @Json(name = "mainState")
    val mainState: EditState,
    @Json(name = "material")
    val material: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "pageCount")
    val pageCount: Int,
    @Json(name = "peripheries")
    val peripheries: List<PeripheryCardModel>,
    @Json(name = "peripheryOverviewModels")
    val peripheryOverviewModels: List<PeripheryOverviewModel>,
    @Json(name = "pictures")
    val pictures: List<PictureModel>,
    @Json(name = "price")
    val price: String?,
    @Json(name = "readerCount")
    val readerCount: Int,
    @Json(name = "relatedEntriesState")
    val relatedEntriesState: EditState,
    @Json(name = "relatedPeripheriesState")
    val relatedPeripheriesState: EditState,
    @Json(name = "saleLink")
    val saleLink: String?,
    @Json(name = "size")
    val size: String?,
    @Json(name = "smallBackgroundPicture")
    val smallBackgroundPicture: String?,
    @Json(name = "songCount")
    val songCount: Int,
    @Json(name = "thumbnail")
    val thumbnail: String?,
    @Json(name = "type")
    val type: PeripheryType
)