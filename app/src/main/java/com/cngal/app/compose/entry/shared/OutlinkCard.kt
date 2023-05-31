package com.cngal.app.compose.entry.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.model.entry.OutlinkModel

@Composable
fun OutlinkCard(model: OutlinkModel, fillMaxWidth: Boolean, onNav: (String) -> Unit)
{
    val modifier = if (fillMaxWidth)
    {
        Modifier.fillMaxWidth()
    }
    else
    {
        Modifier.widthIn(max = 200.dp)
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
                    contentDescription = model.displayName,
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
            }

            Column(
                modifier = Modifier
                    .height(45.dp),
                verticalArrangement = if (!model.displayValue.isNullOrBlank())
                {
                    Arrangement.SpaceBetween
                }
                else
                {
                    Arrangement.Center
                }
            ) {

                Text(
                    text = model.displayName,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium
                )
                if (!model.displayValue.isNullOrBlank())
                {
                    Text(
                        text = model.displayValue,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}