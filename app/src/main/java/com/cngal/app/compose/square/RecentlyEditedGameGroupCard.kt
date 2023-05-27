package com.cngal.app.compose.square

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.cngal.app.model.square.RecentlyEditedGameModel

@Composable
fun RecentlyEditedGameGroupCard(model: List<RecentlyEditedGameModel>)
{
    TitleCard(title = "近期编辑", link = "https://www.cngal.org/search?Sort=LastEditTime%20desc&Types=Game", content = {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            model.forEach() { item ->
                RecentlyEditedGameCard(model = item, onClickCard = {
                    //todo 替换跳转页面
                    openNewTabWindow(
                        "https://www.cngal.org/${item.url}",
                        appContext
                    )
                })
            }
        }
    })
}

@Composable
fun RecentlyEditedGameCard(model: RecentlyEditedGameModel, onClickCard: () -> Unit)
{
    Card( modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .clickable { onClickCard() }
    ) {
        Row(  horizontalArrangement = Arrangement.spacedBy(12.dp),) {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(460f / 215f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .padding(vertical =  12.dp, horizontal = 8.dp)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {

                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = model.publishTime,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }
    }

}