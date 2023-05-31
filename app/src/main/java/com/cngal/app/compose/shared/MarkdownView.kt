package com.cngal.app.compose.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun MarkdownView(modifier: Modifier = Modifier, text: String,onNav:(String)->Unit)
{
    Box(modifier = modifier)
    {
        val textColor = MaterialTheme.colorScheme.onBackground.toArgb()
        val linkTextColor = MaterialTheme.colorScheme.primary.toArgb()
        /*AndroidView(
            factory = {
                TextView(it).apply {
                    // links
                    autoLinkMask = Linkify.WEB_URLS
                    linksClickable = true
                    this.setTextColor(textColor)
                    this.setLinkTextColor(linkTextColor)
                }
            },
            update = {
                it.text = HtmlCompat.fromHtml(text, 0)
            }
        )*/


        MarkdownText(markdown = text, onLinkClicked = {onNav(it)}, color = MaterialTheme.colorScheme.onBackground)
    }
}