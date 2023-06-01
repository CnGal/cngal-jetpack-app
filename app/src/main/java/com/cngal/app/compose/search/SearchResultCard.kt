package com.cngal.app.compose.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.article.shared.ArticleCard
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.periphery.shared.PeripheryCard
import com.cngal.app.compose.tag.shared.TagCard
import com.cngal.app.compose.video.shared.VideoCard
import com.cngal.app.model.search.SearchResultModel

@Composable
fun SearchResultCard(
    modifier: Modifier = Modifier,
    model: List<SearchResultModel>,
    isSegment: Boolean,
    onNav: (String) -> Unit
)
{
    if (isSegment)
    {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            model.forEach {
                SingleSearchResultCard(modifier, it, onNav, false)
            }
        }

    }
    else
    {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            model.forEach {
                Box(Modifier.weight(1f, true)) {
                    SingleSearchResultCard(modifier, it, onNav, true)
                }
            }
        }
    }

}

@Composable
fun SingleSearchResultCard(
    modifier: Modifier = Modifier,
    model: SearchResultModel,
    onNav: (String) -> Unit,
    fixedHeight: Boolean
)
{

    if (model.entry != null)
    {
        EntryCard(modifier, model.entry, !fixedHeight, onNav, fixedHeight)
    }
    else if (model.article != null)
    {
        ArticleCard(model.article, !fixedHeight, onNav, fixedHeight)
    }
    else if (model.video != null)
    {
        VideoCard(model.video, !fixedHeight, onNav, fixedHeight)
    }
    else if (model.tag != null)
    {
        TagCard(modifier, model.tag, !fixedHeight, onNav, fixedHeight)
    }
    else if (model.periphery != null)
    {
        PeripheryCard(modifier, model.periphery, !fixedHeight, onNav, fixedHeight)
    }
}