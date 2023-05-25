package com.cngal.app.compose.home

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel())
{

    val carouselsState by homeViewModel.carousels.collectAsState()
    val newsState by homeViewModel.news.collectAsState()
    val weeklyNewsState by homeViewModel.weeklyNews.collectAsState()
    val upcomingGamesState by homeViewModel.upcomingGames.collectAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(vertical =  12.dp),
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

            if (upcomingGamesState.state == AppState.SUCCESS)
            {
                UpcomingGamesGroupCard(upcomingGamesState.data!!)
            }
        }
    }
}