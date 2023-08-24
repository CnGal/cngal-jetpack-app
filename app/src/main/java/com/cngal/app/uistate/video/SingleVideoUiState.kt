package com.cngal.app.uistate.video

import com.cngal.app.model.entry.OutlinkModel
import com.cngal.app.model.periphery.InformationContentModel

class SingleVideoUiState
{
    var link: String = ""
    var shareText: String = ""
    var information: MutableList<InformationContentModel> = mutableListOf()
    var tags: MutableList<String> = mutableListOf()
    var outlinks: MutableList<OutlinkModel> = mutableListOf()
}