package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.compose.shared.ErrorCard
import com.cngal.app.compose.shared.GalleryCard
import com.cngal.app.compose.shared.LoadingCard
import com.cngal.app.compose.shared.TitleBar
import com.cngal.app.helper.ClipboardHelper
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.entry.SingleEntryViewModel

@Composable
fun SingleEntryScreen(
    modifier: Modifier = Modifier,
    id: Int?,
    viewModel: SingleEntryViewModel = viewModel(),
    onNav: (String) -> Unit,
    onBack: () -> Unit
)
{
    val entryState by viewModel.entry.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val peripheryOverviewState by viewModel.peripheryOverview.collectAsState()

    LaunchedEffect(id) {
        if ((entryState.data?.id ?: 0) != id)
        {
            viewModel.loadEntryData(id)
        }
    }

    if (entryState.state == AppState.LOADING)
    {
        LoadingCard(true)
    }
    else if (id == null || id < 0 || entryState.state == AppState.ERROR)
    {
        Box(modifier = modifier.padding(horizontal = 12.dp)) {
            ErrorCard()
        }
    }
    else if (entryState.state == AppState.SUCCESS)
    {
        val model = entryState.data!!

        Scaffold(
            topBar = {
                TitleBar(
                    title = model.name,
                    onBack = onBack,
                    onClickOpenInBrowser = { onNav(uiState.link) },
                    onClickLink = {
                        ClipboardHelper.textCopy(
                            "链接",
                            uiState.link
                        )
                    },
                    onClickShare = {
                        ClipboardHelper.textCopy(
                            "分享文案",
                            uiState.shareText
                        )
                    })
            },
            content = {
                Column(
                    modifier = modifier
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 0.dp, 12.dp),

                        verticalArrangement = Arrangement.spacedBy(36.dp)
                    ) {

                        MainCard(
                            model.mainPicture,
                            model.thumbnail,
                            uiState.steamId,
                            model.type,
                            model.name,
                            model.briefIntroduction,
                            model.anotherName
                        )

                        TagGroupCard(tags = model.tags, onNav = onNav)
                        ReleaseGroupCard(uiState.releases, model.name, onNav)


                        GalleryCard(images = uiState.images)

                        InformationCard(
                            model.information,
                            model.productionGroups,
                            model.publishers,
                            onNav
                        )

                        StaffCard(staffs = model.staffs, onNav = onNav)

                        MainPageCard(mainPage = model.mainPage, onNav = onNav)

                        VerticalDrawingCard(model.mainPicture, model.name, model.type)

                        NewsGroupCard(uiState.news, model.newsOfEntry, model.name, onNav)

                        RelevanceGroupCard(
                            model.roles,
                            uiState.castWorks,
                            uiState.productionGroupWorks,
                            uiState.publisherWorks,
                            uiState.participationWorks,
                            uiState.appreciatedParticWorks,
                            uiState.games,
                            uiState.groups,
                            uiState.staffs,
                            uiState.roles,
                            model.articleRelevances,
                            peripheryOverviewState.data,
                            model.name,
                            onNav
                        )

                        OutlinkGroupCard(uiState.outlinks, model.name, onNav)
                    }
                }
            }
        )

    }
}
