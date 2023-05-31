package com.cngal.app.compose.video.single

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cngal.app.compose.shared.MarkdownView
import com.cngal.app.compose.shared.TitleCard

@Composable
fun MainPageCard(mainPage: String?,briefIntroduction: String?,onNav:(String)->Unit)
{
    if (mainPage.isNullOrBlank()&&briefIntroduction.isNullOrBlank())
    {
        return
    }

    TitleCard(title = "简介", expandable = true, linkText = null, onClickLink = {}, content = {
        if(!mainPage.isNullOrBlank())
        {
            MarkdownView(modifier = Modifier.padding(horizontal = 12.dp), text = mainPage,onNav=onNav)
        }
        else if(!briefIntroduction.isNullOrBlank())
        {
            Box(Modifier.padding(horizontal = 12.dp)) {
                Text(
                    text = briefIntroduction,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

        }

    })

}