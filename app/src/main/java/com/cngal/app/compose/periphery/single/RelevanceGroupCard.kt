package com.cngal.app.compose.periphery.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SingleEntryDestination
import com.cngal.app.SinglePeripheryDestination
import com.cngal.app.compose.periphery.shared.PeripheryCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.periphery.PeripheryOverviewItemModel
import com.cngal.app.model.periphery.PeripheryOverviewModel
import com.cngal.app.model.periphery.PeripheryOverviewType


@Composable
fun RelevanceGroupCard(
    peripheryOverviewModels: List<PeripheryOverviewModel>,
    onNav: (String) -> Unit
)
{
    if (peripheryOverviewModels.isNotEmpty())
    {
        TitleCard(title = "周边合集", content = {
            Column(
                Modifier.padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                peripheryOverviewModels.forEach { item ->
                    PeripheryOverviewCard(item, onNav)
                }
            }
        })
    }

}

@Composable
fun PeripheryOverviewCard(model: PeripheryOverviewModel, onNav: (String) -> Unit)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onNav(
                    "${
                        when (model.type)
                        {
                            PeripheryOverviewType.Entry -> SingleEntryDestination.route
                            PeripheryOverviewType.Periphery -> SingleEntryDestination.route
                            PeripheryOverviewType.User -> SingleEntryDestination.route
                        }
                    }/${model.objectId}"
                )
            },

        ) {
        Column {
            Row(Modifier.padding(12.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (model.isThumbnail)
                {
                    AsyncImage(
                        model = model.image,
                        contentDescription = model.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(70.dp)
                            .aspectRatio(1f / 1f)
                            .clip(CircleShape)
                    )
                }
                else
                {
                    AsyncImage(
                        model = model.image,
                        contentDescription = model.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(70.dp)
                            .aspectRatio(460f / 215f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }

                Column(
                    modifier = Modifier
                        .height(70.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                    )
                    if (!model.briefIntroduction.isNullOrBlank())
                    {
                        Text(
                            text = model.briefIntroduction,
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 3,
                        )
                    }

                }

            }
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = model.peripheries) { item ->
                    PeripheryCard(item, onNav)
                }
            }
        }

    }
}

