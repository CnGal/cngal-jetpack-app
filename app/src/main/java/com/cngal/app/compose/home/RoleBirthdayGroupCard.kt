package com.cngal.app.compose.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SingleEntryDestination
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.RoleBirthdayModel


@Composable
fun RoleBirthdayGroupCard(model: List<RoleBirthdayModel>, onNav: (String) -> Unit)
{
    TitleCard(title = "🎂 生日快乐", onClickLink = {onNav("https://www.cngal.org/birthday")}, content = {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            model.forEach { item ->
                RoleBirthdayCard(model = item, onNav = onNav)
            }

        }
    })
}

@Composable
fun RoleBirthdayCard(model: RoleBirthdayModel, onNav: (String) -> Unit)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNav("${SingleEntryDestination.route}/${model.id}") }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AsyncImage(
                model = model.mainImage,
                contentDescription = model.name,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .clip(CircleShape)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                    val game =
                        model.addInfors.firstOrNull { it.modifier == "登场游戏" }?.contents?.firstOrNull()?.displayName
                    if (!game.isNullOrBlank())
                    {
                        Text(
                            text = "《${game}》",
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }

                }
                if (!model.briefIntroduction.isNullOrBlank())
                {
                    Text(
                        text = model.briefIntroduction,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }
            }
        }
    }
}