package com.cngal.app.compose.tag.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.entry.shared.EntryCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.compose.tag.shared.TagCard
import com.cngal.app.model.entry.EntryCardModel
import com.cngal.app.model.entry.TagModel
import com.cngal.app.model.tag.TagCardModel



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RelevanceTagGroupCard(
    modifier : Modifier=Modifier,
    tags: List<TagCardModel>,
    onNav: (String) -> Unit
)
{
    if (tags.isEmpty())
    {
        return
    }
    TitleCard(title = "子标签", linkText = null, onClickLink = {}, content = {
        FlowRow(
            modifier = modifier
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            tags.forEach {
                TagCard(it.name, it.id, onNav)
            }
        }
    })
}
