package com.cngal.app.compose.video.single

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
import com.cngal.app.compose.shared.LoadingCard
import com.cngal.app.compose.shared.TitleBar
import com.cngal.app.helper.ClipboardHelper
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.video.SingleVideoViewModel

@Composable
fun SingleVideoScreen(
    modifier: Modifier = Modifier,
    id: Long?,
    viewModel: SingleVideoViewModel = viewModel(),
    onNav: (String) -> Unit,
    onBack: () -> Unit
)
{
    val videoState by viewModel.video.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        if ((viewModel.video.value.data?.id ?: 0) != id)
        {
            viewModel.loadVideoData(id)
        }
    }

    if (videoState.state == AppState.LOADING)
    {
        LoadingCard(true)
    }
    else
    {
        Scaffold(
            topBar = {
                TitleBar(title = videoState.data?.name ?: "", onBack =onBack,
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


                        if (id == null || id < 0 || videoState.state == AppState.ERROR)
                        {
                            Box(modifier = modifier.padding(horizontal = 12.dp)) {
                                ErrorCard()
                            }
                            //return
                        }
                        else
                        {
                            if (videoState.state == AppState.SUCCESS)
                            {
                                val model = videoState.data!!

                                MainCard(
                                    model.mainPicture,
                                    model.name,
                                )
                                AuthorCard(model.userInfor, onNav)
                                TagGroupCard(uiState.tags)
                                InformationCard( uiState.information)
                                MainPageCard(model.mainPage,model.briefIntroduction,onNav)
                                RelevanceGroupCard(
                                    model.relatedArticles,
                                    model.relatedEntries,
                                    model.relatedVideos,
                                    uiState.outlinks,
                                    model.name,
                                    onNav
                                )
                            }
                        }

                    }
                }
            }
        )
    }

}