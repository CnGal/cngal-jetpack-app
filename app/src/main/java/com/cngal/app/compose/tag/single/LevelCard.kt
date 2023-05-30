package com.cngal.app.compose.tag.single

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.compose.tag.shared.TagCard
import com.cngal.app.model.tag.TagLevelModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LevelCard( modifier : Modifier=Modifier,tags: List<TagLevelModel>, onNav: (String) -> Unit)
{
    if (tags.isEmpty())
    {
        return
    }
    TitleCard(title = "父标签", linkText = null, onClickLink = {}, content = {
        FlowRow(
            modifier = modifier
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            tags.dropLast(1).forEach {
                TagCard(it.key, it.value, onNav)
            }
        }
    })
}

