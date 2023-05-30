package com.cngal.app.compose.entry.single

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.compose.shared.TitleCard
import com.cngal.app.model.entry.EntryType

@Composable
fun VerticalDrawingCard(image: String?, name: String, type: EntryType)
{
    if (image.isNullOrBlank() || type == EntryType.Game || type == EntryType.ProductionGroup)
    {
        return
    }
    TitleCard(title = "立绘", linkText = null, onClickLink = {},content = {
        AsyncImage(
            model = image,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(250.dp)
                .clip(RoundedCornerShape(12.dp))
                .padding(horizontal = 12.dp)
        )
    })

}