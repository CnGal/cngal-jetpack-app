package com.cngal.app.compose.square

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.data.square.CommunityData
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.square.CommunityModel

@Composable
@Preview(showBackground = true)
fun CommunityGroupCardPreview()
{
    CommunityGroupCard()
}


@Composable
fun CommunityGroupCard(model: List<CommunityModel> = CommunityData.toList())
{

    TitleCard(title = "社区交流", content = {

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(horizontal = 12.dp)
        ) {
            model.forEach { item ->
                CommunityCard(model = item, onClickCard = {
                    //todo 替换跳转页面
                    openNewTabWindow(
                        item.link,
                        appContext
                    )
                })
            }

        }
    })
}

@Composable
fun CommunityCard(model: CommunityModel, onClickCard: () -> Unit)
{
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClickCard() }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AsyncImage(
                model = model.image,
                contentDescription = model.title,
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column(
                modifier = Modifier
                    .height(40.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = model.title,
                    style = MaterialTheme.typography.titleMedium
                )
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