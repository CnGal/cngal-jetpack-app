package com.cngal.app.compose.tag.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SingleEntryDestination
import com.cngal.app.SingleTagDestination
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.tag.TagCardModel

@Composable
fun TagCard(name: String, id: Int, onNav: (String) -> Unit)
{
    val modifier = if (id > 0)
    {
        Modifier
            .clickable { onNav("tags/index/${id}") }
    }
    else
    {
        Modifier
    }
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Filled.Tag, contentDescription = "标签", Modifier.size(16.dp))
            Spacer(Modifier.size(4.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun TagCard(modifier : Modifier=Modifier, model: TagCardModel, fillMaxWidth: Boolean, onNav: (String) -> Unit,fixedHeight:Boolean=false)
{
    if (fillMaxWidth)
    {
        Card(modifier = modifier
            .fillMaxWidth()
            .clickable { onNav("${SingleTagDestination.route}/${model.id}") }
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
                        .padding(horizontal =  12.dp, vertical = 6.dp)
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
        if(fixedHeight)
        {
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable { onNav("${SingleTagDestination.route}/${model.id}") }
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
                .clickable { onNav("${SingleTagDestination.route}/${model.id}") }
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
