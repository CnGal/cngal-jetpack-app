package com.cngal.app.compose.entry.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SingleEntryDestination
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.EntryType

@Composable
fun EntryCard(model: EntryCardModel, onNav: (String) -> Unit)
{
    if (model.type == EntryType.Game || model.type == EntryType.ProductionGroup)
    {
        GameOrGroupCard(model, onNav)
    }
    else
    {
        RoleOrStaffCard(model, onNav)
    }
}


@Composable
fun GameOrGroupCard(model: EntryCardModel, onNav: (String) -> Unit)
{
    Card(modifier = Modifier
        .width(120.dp)
        .fillMaxHeight()
        .clickable { onNav("${SingleEntryDestination.route}/${model.id}") }
    ) {
        Column {
            AsyncImage(
                model = model.mainImage,
                contentDescription = model.name,
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

@Composable
fun RoleOrStaffCard(model: EntryCardModel, onNav: (String) -> Unit)
{
    Card(modifier = Modifier
        .width(80.dp)
        .fillMaxHeight()
        .clickable {
            onNav("${SingleEntryDestination.route}/${model.id}")
        }
    ) {
        Column {
            AsyncImage(
                model = model.mainImage,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Text(
                text = model.name,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            )
        }
    }
}