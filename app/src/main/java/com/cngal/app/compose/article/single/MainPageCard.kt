package com.cngal.app.compose.article.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Publish
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.MarkdownView
import com.cngal.app.extension.toDate
import com.cngal.app.extension.toString

@Composable
fun MainPageCard(mainPage: String?, createTime: String, lastEditTime: String)
{
    if (mainPage.isNullOrBlank())
    {
        return
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        MarkdownView(modifier = Modifier.padding(horizontal = 12.dp), text = mainPage)
        Column(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            //horizontalAlignment = Alignment.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Filled.Publish,
                    contentDescription = "发布",
                    Modifier.size(16.dp),
                    tint =MaterialTheme.colorScheme.secondary
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
                    tint =MaterialTheme.colorScheme.secondary
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