package com.cngal.app.compose.square

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.square.HotTagModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotTagGroupCard(model: List<HotTagModel>)
{
    TitleCard(title = "热门标签", content = {
        FlowRow(
            modifier = Modifier
                .padding(horizontal = 6.dp)
        ) {
            model.forEach {
                Box(modifier = Modifier.padding(6.dp))
                {
                    HotTagCard(it, onClickCard = {
                        openNewTabWindow(
                            "https://www.cngal.org/${it.url}",
                            appContext
                        )
                    })
                }
            }
        }
    })
}

@Composable
fun HotTagCard(model: HotTagModel, onClickCard: () -> Unit)
{
    Card(modifier = Modifier
        .clickable { onClickCard() }
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Tag, contentDescription = model.name, Modifier.size(16.dp))
            Spacer(Modifier.size(4.dp))
            Text(
                text = model.name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        }
    }
}