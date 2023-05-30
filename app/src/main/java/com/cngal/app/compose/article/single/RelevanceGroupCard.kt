package com.cngal.app.compose.article.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.ArticleCardGroupDestination
import com.cngal.app.EntryCardGroupDestination
import com.cngal.app.compose.article.shared.ArticleCard
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.JsonHelper
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.entry.EntryCardModel
import java.net.URLEncoder


@Composable
fun RelevanceGroupCard(
    relatedArticles: List<ArticleCardModel>,
    relatedEntries: List<EntryCardModel>,
    name: String,
    onNav: (String) -> Unit
)
{
    if (relatedArticles.isNotEmpty())
    {
        TitleCard(title = "关联文章", onClickLink = {
            val title = URLEncoder.encode("${name}的关联文章", "utf-8")
            val json = JsonHelper.toJson(relatedArticles)
            onNav("${ArticleCardGroupDestination.route}/${title}/${json}")
        }, content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = relatedArticles) { item ->
                    ArticleCard(item, false, onNav)
                }
            }
        })
    }

    if (relatedEntries.isNotEmpty())
    {
        TitleCard(title = "关联词条", onClickLink = {
            val title = URLEncoder.encode("${name}的关联词条", "utf-8")
            val json = JsonHelper.toJson(relatedEntries)
            onNav("${EntryCardGroupDestination.route}/${title}/${json}")
        }, content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = relatedEntries) { item ->
                    EntryCard(Modifier,item, false, onNav)
                }
            }
        })
    }
}
