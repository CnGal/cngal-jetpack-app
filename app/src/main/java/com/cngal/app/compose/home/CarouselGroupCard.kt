package com.cngal.app.compose.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cngal.app.compose.shared.Banner
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.home.CarouselModel

@Composable
fun CarouselGroupCard(carousels:List<CarouselModel>
)
{
    //随机取六张轮播图
    carousels.toMutableList().sortByDescending { s -> s.priority }
    val images = carousels.take(3).map { it.image }.toMutableList()
    if (carousels.count() >= 6)
    {
        images.addAll(
            carousels.filter { s: CarouselModel -> !images.contains(s.image) }.shuffled().take(3)
                .map { it.image })
    } else
    {
        images.addAll(carousels.filter { s: CarouselModel -> !images.contains(s.image) }
            .map { it.image })
    }

    //展示轮播图
    Banner(imgs = images, onClickBanner = {
        openNewTabWindow(carousels.find { s: CarouselModel -> s.image == it }?.link ?: "",
            appContext)
    })
}

