package com.cngal.app.compose.square


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.compose.home.CarouselGroupCard
import com.cngal.app.compose.home.LatestArticleGroupCard
import com.cngal.app.compose.home.LatestVideoGroupCard
import com.cngal.app.compose.home.NewsGroupCard
import com.cngal.app.compose.home.PublishedGameGroupCard
import com.cngal.app.compose.home.UpcomingGameGroupCard
import com.cngal.app.compose.home.WeeklyNewsGroupCard
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.home.SquareViewModel

@Composable
fun SquareScreen(modifier: Modifier = Modifier,viewModel: SquareViewModel = viewModel())
{
    val announcementsState by viewModel.announcements.collectAsState()
    val recentlyEditedGamesState by viewModel.recentlyEditedGames.collectAsState()
    val friendLinksState by viewModel.friendLinks.collectAsState()
    val hotTagsState by viewModel.hotTags.collectAsState()
    val latestCommentsState by viewModel.latestComments.collectAsState()

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            if (hotTagsState.state == AppState.SUCCESS)
            {
                HotTagGroupCard(hotTagsState.data!!)
            }
            if (latestCommentsState.state == AppState.SUCCESS)
            {
                LatestCommentGroupCard(latestCommentsState.data!!)
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