package com.cngal.app.compose.entry.single

import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.PictureContentModel

@Composable
fun MainPageCard(mainPage:String?)
{
    if(mainPage.isNullOrBlank())
    {
        return
    }
    TitleCard(title = "简介", expandable = true, content = {
        Box(modifier = Modifier.padding(horizontal = 12.dp))
        {
            AndroidView(
                factory = {
                    TextView(it).apply {
                        // links
                        autoLinkMask = Linkify.WEB_URLS
                        linksClickable = true
                    }
                },
                update = {
                    it.text =  HtmlCompat.fromHtml(mainPage, HtmlCompat.FROM_HTML_MODE_COMPACT)
                }
            )
        }

    })
}