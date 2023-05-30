package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.NewsCardGroupDestination
import com.cngal.app.compose.entry.shared.NewsCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.JsonHelper
import com.cngal.app.model.entry.NewsModel
import java.net.URLEncoder

@Composable
fun NewsGroupCard(news: List<NewsModel>, allNews: List<NewsModel>, name: String, onNav: (String) -> Unit)
{
    if (news.isEmpty())
    {
        return
    }
    TitleCard(title = "动态", onClickLink = {
        val title = URLEncoder.encode("${name}的动态", "utf-8")
        val json = JsonHelper.toJson(allNews)
        onNav("${NewsCardGroupDestination.route}/${title}/${json}")
    }, content = {
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
