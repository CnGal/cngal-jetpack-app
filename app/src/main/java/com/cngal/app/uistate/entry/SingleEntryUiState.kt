package com.cngal.app.uistate.entry

import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.NewsModel
import com.cngal.app.model.entry.OutlinkModel
import com.cngal.app.model.entry.PictureContentModel

class SingleEntryUiState
{
    var steamId: Int? = null
    var images:MutableList<PictureContentModel> = mutableListOf()
    var castWorks: List<EntryCardModel> = listOf()
    var productionGroupWorks: List<EntryCardModel> = listOf()
    var publisherWorks: List<EntryCardModel> = listOf()
    var participationWorks: List<EntryCardModel> = listOf()
    var appreciatedParticWorks: List<EntryCardModel> = listOf()
    var games: List<EntryCardModel> = listOf()
    var groups: List<EntryCardModel> = listOf()
    var staffs: List<EntryCardModel> = listOf()
    var roles: List<EntryCardModel> = listOf()
    var news: List<NewsModel> = listOf()
    var outlinks: MutableList<OutlinkModel> = mutableListOf()
    var link :String=""
    var shareText :String=""
}