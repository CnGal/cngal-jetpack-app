package com.cngal.app.compose.entry.single

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.SingleArticleDestination
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.extension.toDate
import com.cngal.app.extension.toTimeFromNowString
import com.cngal.app.model.entry.NewsModel

@Composable
fun NewsCard(news: List<NewsModel>, onNav: (String) -> Unit)
{
    if (news.isEmpty())
    {
        return
    }
    TitleCard(title = "动态", onClickLink = {}, content = {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            news.forEach { item ->
                NewsCard(item, onNav)
            }
        }
    })
}

@Composable
fun NewsCard(model: NewsModel, onNav: (String) -> Unit)
{
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .clickable { expanded = !expanded }
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AsyncImage(
                    model = model.image,
                    contentDescription = model.title,
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp)
                        .clip(CircleShape)
                        .clickable { onNav("${SingleArticleDestination.route}/${model.groupId}") }
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
                            text = model.groupName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = model.happenedTime.toDate().toTimeFromNowString(),
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(
                        text = model.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )

                }
            }
            if (expanded)
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (!model.briefIntroduction.isNullOrBlank())
                    {
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth(),
                            color= Color.Transparent
                        ) {
                            Text(
                                text = model.briefIntroduction,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                    if (!model.link.isNullOrBlank())
                    {
                        Button(
                            onClick = { onNav(model.link) }
                        ) {
                            Text(
                                text = "查看详情",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }

    }


}