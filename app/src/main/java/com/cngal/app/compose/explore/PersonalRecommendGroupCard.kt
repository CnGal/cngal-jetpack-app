package com.cngal.app.compose.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Unpublished
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.IconChip
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.extension.toDate
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.explore.PersonalRecommendDisplayType.*
import com.cngal.app.model.explore.PersonalRecommendImageCardModel
import com.cngal.app.model.explore.PersonalRecommendModel
import java.time.Instant

@Composable
fun PersonalRecommendGroupCard(model: List<PersonalRecommendModel>)
{

}

@Composable
fun PersonalRecommendCard(
    model: PersonalRecommendModel,
    onClickCard: (PersonalRecommendModel) -> Unit,
    onClickImage: (PersonalRecommendImageCardModel) -> Unit,
    onClickStore: (PersonalRecommendModel) -> Unit,
)
{
    when (model.displayType)
    {
        PlainText -> PlainTextCard(model, onClickCard)
        ImageGames -> ImageGameCard(model, onClickImage)
        Gallery -> GalleryCard(model, onClickStore = onClickStore, onClickGame = onClickCard)
    }
}

@Composable
fun ImageGameCard(
    model: PersonalRecommendModel,
    onClickImage: (PersonalRecommendImageCardModel) -> Unit
)
{
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        ImageCard(model = model.imageCards.first(), onClickImage = onClickImage)
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            model.imageCards.drop(1).take(4).chunked(2).forEach { itemGroup ->
                Box(
                    modifier = Modifier
                        .weight(1f, fill = false)
                )
                {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        itemGroup.forEach { item ->

                            ImageCard(model = item, onClickImage = onClickImage)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCard(
    model: PersonalRecommendImageCardModel,
    onClickImage: (PersonalRecommendImageCardModel) -> Unit
)
{
    Box {
        AsyncImage(model = model.image,
            contentDescription = model.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClickImage(model) })
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
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GalleryCard(
    model: PersonalRecommendModel,
    onClickGame: (PersonalRecommendModel) -> Unit,
    onClickStore: (PersonalRecommendModel) -> Unit
)
{
    Card(modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {

            if (model.name != null)
            {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (model.tags.isNotEmpty())
            {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    model.tags.shuffled().take(2).forEach {
                        IconChip(
                            it,
                            Icons.Filled.Tag,
                            MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
            if (model.briefIntroduction != null)
            {
                Text(
                    text = model.briefIntroduction,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(items = model.images) { item ->
                    AsyncImage(
                        model = item,
                        contentDescription = model.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = { onClickGame(model) }
                ) {
                    Icon(Icons.Filled.Info, contentDescription = "详情页", Modifier.size(16.dp))
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = "详情",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = { onClickStore(model) }
                ) {
                    Icon(Icons.Filled.Store, contentDescription = "Steam")
                    Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Steam",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold
                    )
                }

            }


        }


    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlainTextCard(model: PersonalRecommendModel, onClickCard: (PersonalRecommendModel) -> Unit)
{
    Card(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickCard(model) }
    ) {
        Column {
            AsyncImage(
                model = model.mainPicture,
                contentDescription = model.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(460f / 215f)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {

                if (model.name != null)
                {
                    Text(
                        text = model.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                if (model.release != null)
                {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {

                        if (model.release.time != null && model.release.time.toDate()
                            < Instant.now()
                        )
                        {
                            IconChip(
                                "已发布",
                                Icons.Filled.Publish,
                                MaterialTheme.colorScheme.primary
                            )
                        } else
                        {
                            IconChip(
                                "未发布",
                                Icons.Filled.Unpublished,
                                MaterialTheme.colorScheme.primary
                            )
                        }

                        if (model.release.storeInfor.evaluationCount != null && model.release.storeInfor.evaluationCount > 0)
                        {
                            IconChip(
                                "${"%.0f".format(model.release.storeInfor.recommendationRate!!)}% 好评",
                                Icons.Filled.ThumbUp,
                                MaterialTheme.colorScheme.primary
                            )
                            IconChip(
                                "${model.release.storeInfor.evaluationCount}条评测",
                                Icons.Filled.Article,
                                MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }

                if (model.briefIntroduction != null)
                {
                    Text(
                        text = model.briefIntroduction,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

            }

        }
    }
}