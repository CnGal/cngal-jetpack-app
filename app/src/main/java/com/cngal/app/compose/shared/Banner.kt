package com.cngal.app.compose.shared

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.cngal.app.model.home.CarouselModel
import kotlinx.coroutines.delay

/**
 * 自动轮播图
 * @param imgs 图片集合
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Banner(model: List<CarouselModel>, onClickBanner: (CarouselModel) -> Unit)
{
    val pageCount = model.size
    val pagerState = rememberPagerState(initialPage = 0)

    // 页码转换
    fun pageMapper(index: Int) = (index).floorMod(pageCount)

    Box {
        HorizontalPager(
            pageCount = pageCount,
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) { index ->
            // 计算页面下标
            val page = pageMapper(index)
            BannerItem(model = model[page], onClickBanner)
        }

        var underDragging by remember {
            mutableStateOf(false)
        }

        LaunchedEffect(key1 = Unit) {
            pagerState.interactionSource.interactions.collect { interaction ->
                when (interaction)
                {
                    is PressInteraction.Press -> underDragging = true
                    is PressInteraction.Release -> underDragging = false
                    is PressInteraction.Cancel -> underDragging = false
                    is DragInteraction.Start -> underDragging = true
                    is DragInteraction.Stop -> underDragging = false
                    is DragInteraction.Cancel -> underDragging = false
                }
            }
        }

        if (underDragging.not())
        {
            LaunchedEffect(key1 = underDragging) {
                while (true)
                {
                    delay(1000L)
                    val current = pagerState.currentPage
                    val nextPage = current + 1
                    if (underDragging.not())
                    {
                        val toPage =
                            nextPage.takeIf { nextPage < pageCount }
                                ?: 0

                        pagerState.animateScrollToPage(toPage)
                    }
                }
            }
        }

    }
}

@Composable
fun BannerItem(model: CarouselModel, onClickBanner: ( CarouselModel) -> Unit)
{
    AsyncImage(
        model = model.image, contentDescription = model.note,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1024f / 200f)
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClickBanner(model) }
    )
}

/**
 * 转换下标
 */
private fun Int.floorMod(other: Int): Int = when (other)
{
    0 -> this
    else -> this - floorDiv(other) * other
}
