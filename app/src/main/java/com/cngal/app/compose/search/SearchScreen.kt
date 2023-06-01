package com.cngal.app.compose.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SentimentSatisfiedAlt
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.compose.shared.LoadingCard
import com.cngal.app.compose.shared.TitleBar
import com.cngal.app.helper.ClipboardHelper
import com.cngal.app.viewmodel.search.SearchViewModel
import com.funny.data_saver.core.rememberDataSaverState


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = viewModel(),
    onNav: (String) -> Unit,
    onBack: () -> Unit
)
{
    val searchResultsState by viewModel.searchResults.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var isSegment by rememberDataSaverState("ListDisplayMode", true)

    Scaffold(
        topBar = {
            Column {
                TitleBar(
                    title = "搜索",
                    onBack = onBack,
                    onClickOpenInBrowser = { onNav(uiState.link) },
                    onClickLink = {
                        ClipboardHelper.textCopy(
                            "链接",
                            uiState.link
                        )
                    })
                MainCard(
                    viewModel.text,
                    isSegment,
                    onTextChanged = { viewModel.updateText(it) },
                    onKeyboardDone = { viewModel.search() },
                    OnIsSegmentChanged = {isSegment=it})
            }

        },
        content = { paddingValues ->
            Box(
                Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 12.dp)
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = searchResultsState, key = {
                        val model=it.first()
                        if (model.entry != null)
                        {
                            "entry${model.entry.id}"
                        }
                        else if (model.article != null)
                        {
                            "article${model.article.id}"
                        }
                        else if (model.tag != null)
                        {
                            "tag${model.tag.id}"
                        }
                        else if (model.video != null)
                        {
                            "video${model.video.id}"
                        }
                        else if (model.periphery != null)
                        {
                            "periphery${model.periphery.id}"
                        }
                        else
                        {
                            "other"
                        }
                    }) { item ->
                        SearchResultCard(Modifier, item, isSegment, onNav)
                    }

                    item {
                        if (!viewModel.isCompleted)
                        {

                            LoadingCard()
                            LaunchedEffect(Unit) {
                                viewModel.loadMoreResults()
                            }

                        }
                        else if (searchResultsState.isEmpty())
                        {
                            EmptyCard()
                        }
                    }
                }
            }

        }
    )

}

@Preview
@Composable
fun EmptyCard()
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(
                Icons.Rounded.SentimentSatisfiedAlt,
                contentDescription = "错误",
                Modifier.size(48.dp)
            )
            Text(text = "搜索不到呢~")
        }
    }
}