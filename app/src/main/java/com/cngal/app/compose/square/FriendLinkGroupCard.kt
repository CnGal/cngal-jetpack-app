package com.cngal.app.compose.square

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.square.FriendLinkModel


@Composable
fun FriendLinkGroupCard(model:List<List<FriendLinkModel>>)
{
    TitleCard(title = "友情链接", content = {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            model.forEach { itemGroup ->
                Box(
                    modifier = Modifier
                        .weight(1f, fill = false)
                )
                {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        itemGroup.forEach { item ->

                            FriendLinkCard(model = item, onClickCard = {
                                //todo 替换跳转页面
                                openNewTabWindow(
                                    item.url,
                                    appContext
                                )
                            })
                        }

                    }
                }
            }
        }
    })

}

@Composable
fun FriendLinkCard(model: FriendLinkModel, onClickCard: () -> Unit)
{
    Card(modifier = Modifier
            .clickable { onClickCard() }
    ) {
        Column {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .aspectRatio(460f / 215f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .padding(12.dp),
            ) {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }

}