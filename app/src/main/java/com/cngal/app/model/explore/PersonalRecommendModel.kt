package com.cngal.app.model.explore


import com.cngal.app.model.entry.ReleaseModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonalRecommendModel(
    @Json(name = "briefIntroduction")
    val briefIntroduction: String?,
    @Json(name = "displayType")
    val displayType: PersonalRecommendDisplayType,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imageCards")
    val imageCards: List<PersonalRecommendImageCardModel>,
    @Json(name = "images")
    val images: List<String>,
    @Json(name = "mainPicture")
    val mainPicture: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "release")
    val release: ReleaseModel?,
    @Json(name = "steamId")
    val steamId: String?,
    @Json(name = "tags")
    val tags: List<String>
)

 enum class PersonalRecommendDisplayType
{
    PlainText,
    ImageGames,
    Gallery
}
