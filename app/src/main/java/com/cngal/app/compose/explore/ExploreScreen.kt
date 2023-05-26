package com.cngal.app.compose.explore

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.home.ExploreViewModel

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier, viewModel: ExploreViewModel = viewModel()
)
{
    val evaluationsState by viewModel.evaluations.collectAsState()
    val personalRecommends by viewModel.personalRecommends.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        item(key = "EvaluationGroupCard") {
            if (evaluationsState.state == AppState.SUCCESS)
            {
                Box(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 36.dp)
                ) {
                    EvaluationGroupCard(evaluationsState.data!!)
                }
            }
        }
        item(key = "TitleCard") {
            TitleCard(title = "随机推荐", content = {})
        }
        items(items = personalRecommends, key = {
            it.id
        }) { item ->
            Box(
                modifier = Modifier
                    .padding(12.dp, 8.dp, 12.dp, 24.dp)
            ) {
                PersonalRecommendCard(model = item, onClickCard = {
                    //todo 替换跳转页面
                    openNewTabWindow(
                        "https://www.cngal.org/entries/index/${it.id}", appContext
                    )
                }, onClickImage = {
                    //todo 替换跳转页面
                    openNewTabWindow(
                        "https://www.cngal.org/entries/index/${it.id}", appContext
                    )
                }, onClickStore = {
                    //todo 替换跳转页面
                    openNewTabWindow(
                        "https://store.steampowered.com/app/${it.steamId}", appContext
                    )
                })
            }

        }

        item {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
            ) {
                if (personalRecommends.count() < 100)
                {
                    LoadingCard()
                    LaunchedEffect(Unit) {
                        viewModel.loadMoreRecommends()
                    }

                }
                else
                {
                    ReloadCard(onClickRefresh = {
                       viewModel.refreshRecommends()
                    })
                }
            }
        }

    }
}

@Preview
@Composable
fun ReloadCard(onClickRefresh:()->Unit={})
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = onClickRefresh) {

                Icon(Icons.Filled.Refresh, contentDescription = "重新加载数据")
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(text = "重新加载数据")

            }
        }
    }
}