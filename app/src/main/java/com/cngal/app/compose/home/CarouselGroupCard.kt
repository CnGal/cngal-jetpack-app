package com.cngal.app.compose.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.cngal.app.compose.shared.Banner
import com.cngal.app.helper.appContext
import com.cngal.app.helper.openNewTabWindow
import com.cngal.app.model.home.CarouselModel

@Composable
fun CarouselGroupCard(
    model: List<CarouselModel>
)
{
    //展示轮播图
    Banner(model = model, onClickBanner = {
        openNewTabWindow(
            it.link,
            appContext
        )
    })
}

