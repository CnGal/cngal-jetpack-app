package com.cngal.app.compose.entry.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.GameReleaseType
import com.cngal.app.uistate.entry.ReleaseUiState

@Composable
fun ReleaseGroupCard(release: List<ReleaseUiState>, name: String, onNav: (String) -> Unit)
{
    if (release.isEmpty())
    {
        return
    }
    TitleCard(title = "发行列表", content = {
        LazyRow(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = release) { item ->
                ReleaseCard(item, false, onNav)
            }
        }
    })
}


@Composable
fun ReleaseCard(model: ReleaseUiState, fillMaxWidth: Boolean, onNav: (String) -> Unit)
{
    val modifier = if (fillMaxWidth)
    {
        Modifier.fillMaxWidth()
    }
    else
    {
        Modifier.widthIn(max = 250.dp)
    }
    Card(
        modifier = modifier
            .clickable { if (!model.link.isNullOrBlank()) onNav(model.link) }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (!model.image.isNullOrBlank())
            {
                AsyncImage(
                    model = model.image,
                    contentDescription = model.name,
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Column(
                modifier = Modifier
                    .height(45.dp),
                verticalArrangement = if (!model.value.isNullOrBlank())
                {
                    Arrangement.SpaceBetween
                }
                else
                {
                    Arrangement.Center
                }
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = model.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium
                    )
                    ReleaseTypeChip(model.type)
                }

                if (!model.value.isNullOrBlank())
                {
                    Text(
                        text = model.value,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}


@Composable
fun ReleaseTypeChip(releaseType: GameReleaseType)
{
    if (releaseType == GameReleaseType.Official)
    {
        return
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 1.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Text(
                text = releaseType.toString(),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
