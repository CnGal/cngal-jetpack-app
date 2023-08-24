package com.cngal.app.uistate.periphery

import com.cngal.app.model.entry.PictureContentModel
import com.cngal.app.model.periphery.InformationContentModel

class SinglePeripheryUiState
{
    var link :String=""
    var shareText :String=""
    var images:MutableList<PictureContentModel> = mutableListOf()
    var information: MutableList<InformationContentModel> = mutableListOf()
    var tags: MutableList<String> = mutableListOf()
}