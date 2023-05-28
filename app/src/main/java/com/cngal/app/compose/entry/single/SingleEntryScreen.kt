package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cngal.app.compose.shared.ErrorCard
import com.cngal.app.compose.shared.LoadingCard
import com.cngal.app.model.shared.AppState
import com.cngal.app.viewmodel.entry.SingleEntryViewModel

@Composable
fun SingleEntryScreen(
    modifier: Modifier = Modifier,
    id: Int?,
    viewModel: SingleEntryViewModel = viewModel(),
    onNav: (String) -> Unit
)
{
    val entryState by viewModel.entry.collectAsState()
    val uiState by viewModel.uiState.collectAsState()

    if (entryState.state == AppState.LOADING)
    {
        LoadingCard(true)
    }
    else
    {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 0.dp, 12.dp),

                verticalArrangement = Arrangement.spacedBy(36.dp)
            ) {


                if (id == null || id < 0 || entryState.state == AppState.ERROR)
                {
                    Box(modifier = modifier.padding(horizontal = 12.dp, vertical = 36.dp)) {
                        ErrorCard()
                    }
                    return
                }

                if (entryState.state == AppState.EMPTY)
                {
                    LaunchedEffect(Unit) {
                        viewModel.loadEntryData(id)
                    }
                }

                if (entryState.state == AppState.SUCCESS)
                {
                    val model = entryState.data!!

                    MainCard(
                        model.mainPicture ?: model.thumbnail,
                        uiState.steamId,
                        model.type,
                        model.name,
                        model.briefIntroduction,
                        model.anotherName
                    )

                    TagGroupCard(tags = model.tags, onNav = onNav)

                    GalleryCard(images = uiState.images)

                    InformationCard(
                        model.information.firstOrNull(),
                        model.productionGroups,
                        model.publishers,
                        onNav
                    )

                    StaffCard(staffs = model.staffs, onNav = onNav)

                    MainPageCard(mainPage = model.mainPage)

                    RoleGroupCard(model.roles, onNav)
                }
            }
        }
    }

}
