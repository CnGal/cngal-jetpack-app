package com.cngal.app.compose.article.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.model.user.UserInforModel

@Composable
fun AuthorCard(user: UserInforModel, onNav: (String) -> Unit)
{

    Row(
        Modifier.padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            model = user.photoPath,
            contentDescription = user.name,
            modifier = Modifier
                .height(45.dp)
                .width(45.dp)
                .clip(CircleShape)
                .clickable { /*todo*/ }
        )
        Column(
            modifier = Modifier
                .height(40.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            if (!user.personalSignature.isNullOrBlank())
            {
                Text(
                    text = user.personalSignature,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }


}