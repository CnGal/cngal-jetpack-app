package com.cngal.app.compose.article.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.IconChip
import com.cngal.app.compose.shared.MarkdownView
import com.cngal.app.extension.toDate
import com.cngal.app.extension.toString

@Composable
fun MainPageCard(
    mainPage: String?,
    createTime: String,
    lastEditTime: String,
    originalAuthor: String?,
    originalLink: String?,
    onNav: (String) -> Unit
)
{
    if (mainPage.isNullOrBlank())
    {
        return
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth(),
    ) {
        ReprintAlertCard(originalAuthor, originalLink, onNav)
        MarkdownView(text = mainPage, onNav=onNav )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top=12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            //horizontalAlignment = Alignment.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Publish,
                    contentDescription = "发布",
                    Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = "发布于 ${createTime.toDate().toString("yyyy-MM-dd HH:mm")} ",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Edit,
                    contentDescription = "最后编辑",
                    Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    text = "最后编辑于 ${lastEditTime.toDate().toString("yyyy-MM-dd HH:mm")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ReprintAlertCard(originalAuthor: String?, originalLink: String?, onNav: (String) -> Unit)
{
    if (originalAuthor.isNullOrBlank() || originalLink.isNullOrBlank())
    {
        return
    }
    FlowRow(
        modifier = Modifier
            .padding(bottom = 36.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        IconChip(
            text = "搬运",
            icon = Icons.Filled.SwapHoriz,
            color = MaterialTheme.colorScheme.primary
        )
        IconChip(
            text = originalAuthor,
            icon = Icons.Filled.Person,
            color = MaterialTheme.colorScheme.primary
        )
        IconChip(
            modifier=Modifier.clickable{onNav(originalLink)},
            text = originalLink,
            icon = Icons.Filled.Link,
            color = MaterialTheme.colorScheme.primary
        )
    }
}