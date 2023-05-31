package com.cngal.app.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.IconChip
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.home.UpcomingGameModel

@Composable
fun UpcomingGameGroupCard(model: List<UpcomingGameModel>,onNav: (String) -> Unit)
{
    TitleCard(title = "即将发布", onClickLink = {onNav("https://www.cngal.org/times")}, content = {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 12.dp),
        ) {
            items(items = model) { item ->

                UpcomingGameCard(model = item, onClickCard = {
                    onNav.invoke(item.url)
                })
            }
        }
    })
}

@Composable
fun UpcomingGameCard(model: UpcomingGameModel, onClickCard: () -> Unit)
{
    Card(modifier = Modifier
            .width(250.dp)
            .fillMaxHeight()
            .clickable { onClickCard() }
    ) {
        Column {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(460f / 215f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                IconChip(
                   text= model.publishTime,
                   icon= Icons.Filled.Schedule
                )

            }

        }
    }

}