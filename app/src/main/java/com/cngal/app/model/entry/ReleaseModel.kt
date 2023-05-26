package com.cngal.app.model.entry


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReleaseModel(
    @Json(name = "engine")
    val engine: String?,
    @Json(name = "gamePlatformTypes")
    val gamePlatformTypes: List<GamePlatformType>,
    @Json(name = "link")
    val link: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "publishPlatformName")
    val publishPlatformName: String?,
    @Json(name = "publishPlatformType")
    val publishPlatformType: PublishPlatformType,
    @Json(name = "storeInfor")
    val storeInfor: StoreInforModel,
    @Json(name = "time")
    val time: String?,
    @Json(name = "timeNote")
    val timeNote: String?,
    @Json(name = "type")
    val type: GameReleaseType
)

 enum class GameReleaseType
{
    Official,
    Demo,
    EA
}

 enum class GamePlatformType
{
    Windows,
    Linux,
    Mac,
    IOS,
    Android,
    PS,
    NS,
    DOS,
    HarmonyOS,
    H5
}