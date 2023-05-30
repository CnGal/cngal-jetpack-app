package com.cngal.app.compose.tag.single

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.shared.ErrorCard
import com.cngal.app.compose.shared.LoadingCard
import com.cngal.app.compose.shared.TitleBar
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.ClipboardHelper
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.tag.SingleTagViewModel

@Composable
fun SingleTagScreen(
    modifier: Modifier = Modifier,
    id: Int?,
    viewModel: SingleTagViewModel = viewModel(),
    onNav: (String) -> Unit
)
{
    val tagState by viewModel.tag.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        if ((viewModel.tag.value.data?.id ?: 0) != id)
        {
            viewModel.loadArticleData(id)
        }
    }

    if (tagState.state == AppState.LOADING)
    {
        LoadingCard(true)
    }
    else
    {
        Scaffold(
            topBar = {
                TitleBar(title = tagState.data?.name ?: "",
                    onBack = {},
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
                LazyColumn(
                    modifier = modifier
                        .padding(it)
                ) {
                    if (id == null || id < 0 || tagState.state == AppState.ERROR)
                    {
                        item {
                            Box(modifier = Modifier.padding(horizontal = 12.dp)) {
                                ErrorCard()
                            }
                        }
                    }
                    else
                    {
                        if (tagState.state == AppState.SUCCESS)
                        {
                            val model = tagState.data!!

                            item {
                                MainCard(
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 36.dp),
                                    model.mainPicture,
                                    model.briefIntroduction,
                                    model.name,
                                )
                            }
                            item {
                                LevelCard(
                                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 36.dp),
                                    model.tagLevels,
                                    onNav
                                )
                            }
                            if (model.childrenTags.isNotEmpty())
                            {
                                item {
                                    RelevanceTagGroupCard(
                                        modifier = Modifier.padding(
                                            0.dp,
                                            0.dp,
                                            0.dp,
                                            12.dp
                                        ), model.childrenTags, onNav
                                    )
                                }
                            }

                            if (model.childrenEntries.isNotEmpty())
                            {
                                item {
                                    TitleCard(modifier = Modifier.padding(
                                        0.dp,
                                        0.dp,
                                        0.dp,
                                        8.dp
                                    ),
                                        title = "子词条",
                                        linkText = null,
                                        onClickLink = {},
                                        content = { })
                                }
                                items(items = model.childrenEntries) { item ->
                                    EntryCard(
                                        Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp),
                                        item,
                                        true,
                                        onNav,
                                    )
                                }
                            }
                        }
                    }

                }
            }
        )
    }
}