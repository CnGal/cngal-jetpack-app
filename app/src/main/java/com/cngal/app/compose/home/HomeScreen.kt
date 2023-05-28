package com.cngal.app.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.compose.square.AnnouncementGroupCard
import com.cngal.app.compose.square.CommunityGroupCard
import com.cngal.app.compose.square.FriendLinkGroupCard
import com.cngal.app.compose.square.RecentlyEditedGameGroupCard
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel(),onNav:(String)->Unit
)
{

    val carouselsState by viewModel.carousels.collectAsState()
    val newsState by viewModel.news.collectAsState()
    val weeklyNewsState by viewModel.weeklyNews.collectAsState()
    val upcomingGamesState by viewModel.upcomingGames.collectAsState()
    val publishedGamesState by viewModel.publishedGames.collectAsState()
    val latestArticlesState by viewModel.latestArticles.collectAsState()
    val latestVideosState by viewModel.latestVideos.collectAsState()
    val freeGamesState by viewModel.freeGames.collectAsState()
    val discountGamesState by viewModel.discountGames.collectAsState()
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {

            if (carouselsState.state == AppState.SUCCESS)
            {
                CarouselGroupCard(carouselsState.data!!)
            }

            if (newsState.state == AppState.SUCCESS)
            {
                NewsGroupCard(newsState.data!!)
            }

            if (weeklyNewsState.state == AppState.SUCCESS)
            {
                WeeklyNewsGroupCard(weeklyNewsState.data!!)
            }
            if (publishedGamesState.state == AppState.SUCCESS)
            {
                PublishedGameGroupCard(publishedGamesState.data!!,onNav)
            }
            if (upcomingGamesState.state == AppState.SUCCESS)
            {
                UpcomingGameGroupCard(upcomingGamesState.data!!,onNav)
            }
            if (freeGamesState.state == AppState.SUCCESS)
            {
                FreeGameGroupCard(freeGamesState.data!!,onNav)
            }
            if (discountGamesState.state == AppState.SUCCESS)
            {
                DiscountGameGroupCard(discountGamesState.data!!,onNav)
            }
            if (latestArticlesState.state == AppState.SUCCESS)
            {
                LatestArticleGroupCard(latestArticlesState.data!!)
            }
            if (latestVideosState.state == AppState.SUCCESS)
            {
                LatestVideoGroupCard(latestVideosState.data!!)
            }
        }
    }
}
