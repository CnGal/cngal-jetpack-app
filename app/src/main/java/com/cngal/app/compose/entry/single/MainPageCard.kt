package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.MarkdownView
import com.cngal.app.compose.shared.TitleCard

@Composable
fun MainPageCard(mainPage: String?,onNav:(String)->Unit)
{
    if (mainPage.isNullOrBlank())
    {
        return
    }
    TitleCard(title = "简介", expandable = true, linkText = null, onClickLink = {}, content = {
        MarkdownView(modifier = Modifier.padding(horizontal = 12.dp), text = mainPage,onNav=onNav)
    })
}