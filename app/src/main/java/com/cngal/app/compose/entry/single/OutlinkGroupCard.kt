package com.cngal.app.compose.entry.single

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.NewsCardGroupDestination
import com.cngal.app.OutlinkCardGroupDestination
import com.cngal.app.compose.entry.shared.OutlinkCard
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.helper.JsonHelper
import com.cngal.app.model.entry.OutlinkModel
import java.net.URLEncoder

@Composable
fun OutlinkGroupCard(outlinks: List<OutlinkModel>,name:String, onNav: (String) -> Unit)
{
    if (outlinks.isEmpty())
    {
        return
    }
    TitleCard(title = "外部链接", onClickLink = {
        val title = URLEncoder.encode("${name}的外部链接", "utf-8")
        val json = JsonHelper.toJson(outlinks)
        onNav("${OutlinkCardGroupDestination.route}/${title}/${json}")
    }, content = {
        LazyRow(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = outlinks) { item ->
                OutlinkCard(item,false, onNav)
            }
        }
    })
}

