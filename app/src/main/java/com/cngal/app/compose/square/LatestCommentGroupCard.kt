package com.cngal.app.compose.square

import android.text.util.Linkify
import android.widget.TextView
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.TO_HTML_PARAGRAPH_LINES_INDIVIDUAL
import coil.compose.AsyncImage
import com.cngal.app.compose.home.WeeklyNewsCard
import com.cngal.app.compose.shared.MarkdownView
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.extension.toDate
import com.cngal.app.extension.toTimeFromNowString
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.square.LatestCommentModel

@Composable
fun LatestCommentGroupCard(model: List<LatestCommentModel>,onNav: (String) -> Unit)
{
    TitleCard(title = "近期留言",linkText = null, onClickLink = {}, content = {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(horizontal = 12.dp)
    ) {
        model.forEach { item ->
            LatestCommentCard(model = item,onClickImage={
                //todo 替换跳转页面
                openNewTabWindow(
                    "https://www.cngal.org/space/index/${item.userId}",
                    appContext
                )
            },onClickCard={
                //todo 替换跳转页面
                openNewTabWindow(
                    "https://www.cngal.org/${item.url}",
                    appContext
                )
            },onNav=onNav)
        }

    }
})
}

@Composable
fun LatestCommentCard(model:LatestCommentModel, onClickImage: () -> Unit, onClickCard: () -> Unit,onNav: (String) -> Unit)
{
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickCard() }
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            AsyncImage(
                model = model.userImage,
                contentDescription = model.userName,
                modifier = Modifier
                    .height(45.dp)
                    .width(45.dp)
                    .clip(CircleShape)
                    .clickable { onClickImage() }
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment =  Alignment.CenterVertically,
                ) {
                    Text(
                        text = model.userName,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = model.time,
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                MarkdownView(text =  model.content, onNav =onNav )
            }
        }
    }
}