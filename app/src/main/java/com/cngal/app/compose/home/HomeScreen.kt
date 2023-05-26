package com.cngal.app.compose.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel()
)
{

    val carouselsState by viewModel.carousels.collectAsState()
    val newsState by viewModel.news.collectAsState()
    val weeklyNewsState by viewModel.weeklyNews.collectAsState()
    val upcomingGamesState by viewModel.upcomingGames.collectAsState()
    val publishedGamesState by viewModel.publishedGames.collectAsState()
    val latestArticlesState by viewModel.latestArticles.collectAsState()
    val latestVideosState by viewModel.latestVideos.collectAsState()
    val announcementsState by viewModel.announcements.collectAsState()
    val recentlyEditedGamesState by viewModel.recentlyEditedGames.collectAsState()
    val friendLinksState by viewModel.friendLinks.collectAsState()

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
                PublishedGameGroupCard(publishedGamesState.data!!)
            }
            if (upcomingGamesState.state == AppState.SUCCESS)
            {
                UpcomingGameGroupCard(upcomingGamesState.data!!)
            }
            if (latestArticlesState.state == AppState.SUCCESS)
            {
                LatestArticleGroupCard(latestArticlesState.data!!)
            }
            if (latestVideosState.state == AppState.SUCCESS)
            {
                LatestVideoGroupCard(latestVideosState.data!!)
            }
            if (announcementsState.state == AppState.SUCCESS)
            {
                AnnouncementGroupCard(announcementsState.data!!)
            }
            if (recentlyEditedGamesState.state == AppState.SUCCESS)
            {
                RecentlyEditedGameGroupCard(recentlyEditedGamesState.data!!)
            }

            CommunityGroupCard()

            if (friendLinksState.state == AppState.SUCCESS)
            {
                FriendLinkGroupCard(friendLinksState.data!!)
            }
        }
    }
}