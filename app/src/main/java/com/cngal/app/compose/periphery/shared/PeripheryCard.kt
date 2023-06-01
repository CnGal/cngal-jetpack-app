package com.cngal.app.compose.periphery.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SinglePeripheryDestination
import com.cngal.app.model.periphery.PeripheryCardModel
import com.cngal.app.model.periphery.PeripheryOverviewItemModel


@Composable
fun PeripheryCard(
    modifier: Modifier = Modifier,
    model: PeripheryCardModel,
    fillMaxWidth: Boolean,
    onNav: (String) -> Unit,
    fixedHeight: Boolean = false
)
{
    if (fillMaxWidth)
    {
        Card(modifier = modifier
            .fillMaxWidth()
            .clickable { onNav("${SinglePeripheryDestination.route}/${model.id}") }
        ) {
            Row {
                AsyncImage(
                    model = model.mainImage,
                    contentDescription = model.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(70.dp)
                        .aspectRatio(460f / 215f)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .height(58.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                    if (!model.briefIntroduction.isNullOrBlank())
                    {
                        Text(
                            text = model.briefIntroduction,
                            style = MaterialTheme.typography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                        )
                    }

                }

            }
        }
    }
    else
    {
        if (fixedHeight)
        {
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable { onNav("${SinglePeripheryDestination.route}/${model.id}") }
            ) {
                Column {
                    AsyncImage(
                        model = model.mainImage,
                        contentDescription = model.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(460f / 215f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.bodyMedium,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                }
            }
        }
        else
        {
            Card(modifier = Modifier
                .width(120.dp)
                .clickable { onNav("${SinglePeripheryDestination.route}/${model.id}") }
            ) {
                Column {
                    AsyncImage(
                        model = model.mainImage,
                        contentDescription = model.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(460f / 215f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    )
                }
            }
        }

    }

}


@Composable
fun PeripheryCard(
    model: PeripheryOverviewItemModel,
    onNav: (String) -> Unit,
    changeBackground: Boolean = true
)
{
    Card(
        modifier = Modifier
            .width(120.dp)
            .clickable { onNav("${SinglePeripheryDestination.route}/${model.id}") },
        colors = if (changeBackground)
        {
            CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        }
        else
        {
            CardDefaults.cardColors()
        },
    ) {
        Column {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(460f / 215f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = model.name,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(6.dp)
            )
        }
    }

}

