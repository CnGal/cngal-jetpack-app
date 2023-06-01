package com.cngal.app.compose.periphery.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.compose.tag.shared.TagCard
import com.cngal.app.model.entry.TagModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagGroupCard(
    tags: List<String>,
)
{

    if (tags.isEmpty())
    {
        return
    }
    TitleCard(title = "标签", linkText = null, onClickLink = {}, content = {
        FlowRow(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            tags.forEach {
                TagCard(it, 0, onNav={})
            }
        }
    })
}
