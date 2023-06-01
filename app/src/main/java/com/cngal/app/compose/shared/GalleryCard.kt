package com.cngal.app.compose.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.cngal.app.model.entry.PictureContentModel

@Composable
fun GalleryCard(images: List<PictureContentModel>)
{
    if (images.isEmpty())
    {
        return
    }

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var index by rememberSaveable { mutableIntStateOf(0) }


    Column {
        TitleCard(title = "相册", linkText = null, onClickLink = {}, content = {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                items(items = images) { item ->
                    AsyncImage(
                        model = item.url,
                        contentDescription = item.note,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                            .aspectRatio(16f / 9f)
                            .clickable {
                                showDialog = true
                                index = images.indexOf(item)
                            }
                            .clip(RoundedCornerShape(12.dp))

                    )
                }
            }
        })

        if (showDialog)
        {
            Dialog(
                onDismissRequest = { showDialog = !showDialog },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    FullScreenImage(url = images[index].url, onClick = { showDialog = !showDialog })
                }
            }

        }
    }

}

@Composable
fun FullScreenImage(
    modifier: Modifier = Modifier,
    url: String,
    onClick: () -> Unit = {}
)
{

    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val state = rememberTransformableState { zoomChange, _, _ ->
        scale = (zoomChange * scale).coerceAtLeast(1f)
    }

    Surface(
        color = Color.Transparent,
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        scale = 1f
                        offset = Offset.Zero
                    },
                    onTap = {
                        onClick.invoke()
                    }
                )
            }
    ) {
        AsyncImage(
            model = url,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
                .transformable(state = state)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    translationX = offset.x,
                    translationY = offset.y
                )
                .pointerInput(Unit) {
                    detectDragGestures { _, dragAmount ->
                        offset += dragAmount
                    }
                }

        )
    }
}
