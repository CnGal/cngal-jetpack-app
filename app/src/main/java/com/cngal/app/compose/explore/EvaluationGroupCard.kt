package com.cngal.app.compose.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.explore.EvaluationArticleModel
import com.cngal.app.model.explore.EvaluationModel

@Composable
fun EvaluationGroupCard(model: List<EvaluationModel>)
{
    val items =   model.take(1)

    Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
        items.forEach { item ->
                EvaluationCard(model = item, onClickImage = {
                    //todo 替换跳转页面
                    openNewTabWindow(
                        "https://www.cngal.org/${item.url}", appContext
                    )
                })
            }
        }
}

@Composable
fun EvaluationCard(model: EvaluationModel, onClickImage: () -> Unit)
{
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Box {
            AsyncImage(model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onClickImage() })
            Text(
                text = model.name,
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(12.dp)
                    .align(alignment = Alignment.BottomStart)
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            model.articles.shuffled().take(2).forEach { item ->
                Box(
                    modifier = Modifier.weight(1f, fill = false)
                ) {

                    EvaluationArticleCard(model = item, onClickCard = {
                        //todo 替换跳转页面
                        openNewTabWindow(
                            "https://www.cngal.org/articles/index/${item.id}", appContext
                        )
                    })

                }
            }
        }
    }
}

@Composable
fun EvaluationArticleCard(model: EvaluationArticleModel, onClickCard: () -> Unit)
{
    Card( modifier = Modifier
        .width(200.dp)
        .fillMaxHeight()
        .clickable { onClickCard() }) {
        Column {
            AsyncImage(
                model = model.image,
                contentDescription = model.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(460f / 215f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.height(42.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    Text(
                        text = model.originalAuthor,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Text(
                        text = model.type.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

            }
        }

    }
}