package com.cngal.app.uistate.entry

import com.cngal.app.model.entry.GameReleaseType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ReleaseUiState(
    val name: String,
    val value: String?,
    val id: Int,
    val image: String?,
    val link: String?,
    val type: GameReleaseType
)