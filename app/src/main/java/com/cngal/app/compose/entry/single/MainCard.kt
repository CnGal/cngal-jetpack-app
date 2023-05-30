package com.cngal.app.compose.entry.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.model.entry.EntryType

@Composable
fun MainCard(
    mainImage: String?,
    thumbnail: String?,
    steamId: Int?,
    type: EntryType,
    name: String,
    briefIntroduction: String?,
    anotherName: String?,
)
{
    if (type == EntryType.Game || type == EntryType.ProductionGroup)
    {
        GameOrGroupCard(mainImage, name, briefIntroduction, anotherName)
    }
    else
    {
        RoleOrStaffCard(mainImage, thumbnail, name, briefIntroduction, anotherName)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameOrGroupCard(
    image: String?,
    name: String,
    briefIntroduction: String?,
    anotherName: String?,
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(460f / 215f)
                .padding(horizontal = 12.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )

                    if (!anotherName.isNullOrEmpty())
                    {
                        Text(
                            text = anotherName,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
            }
            if (!briefIntroduction.isNullOrBlank())
            {
                Text(
                    text = briefIntroduction,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RoleOrStaffCard(
    mainImage: String?,
    thumbnail: String?,
    name: String,
    briefIntroduction: String?,
    anotherName: String?,
)
{
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            model = thumbnail,
            contentDescription = name,
            modifier = Modifier
                .height(80.dp)
                .width(80.dp)
                .clip(CircleShape)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (!anotherName.isNullOrEmpty())
                {
                    Text(
                        text = anotherName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
            if (!briefIntroduction.isNullOrBlank())
            {
                Text(
                    text = briefIntroduction,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }

    }

}