package com.cngal.app.compose.home

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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.home.NewsModel

@Composable
@Preview
fun NewsGroupCardPreview()
{
    NewsGroupCard(listOf())
}


@Composable
fun NewsGroupCard(model: List<NewsModel>)
{
    val items = model.take(12).chunked(3)

    TitleCard(title = "最新动态", link = "https://www.cngal.org/articles/news", content = {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = items) { item ->
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    item.forEach { news ->
                        NewsCard(model = news, onClickImage = {
                            //todo 替换跳转页面
                            openNewTabWindow(
                                "https://www.cngal.org/entries/index/${news.groupId}",
                                appContext
                            )

                        }, onClickCard = {
                            //todo 替换跳转页面
                            openNewTabWindow(
                                news.link,
                                appContext
                            )
                        })
                    }
                }
            }
        }
    })
}

@Composable
fun NewsCard(model: NewsModel, onClickImage: () -> Unit, onClickCard: () -> Unit)
{
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        modifier = Modifier
            .width(300.dp)
            .clickable { onClickCard() }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment =  Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = model.image,
                contentDescription = model.title,
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .clip(CircleShape)
                    .clickable { onClickImage() }
            )
            Column(
                modifier = Modifier
                    .height(40.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment =  Alignment.CenterVertically,
                ) {
                    Text(
                        text = model.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "11小时前",
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Text(
                    text = model.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }

}