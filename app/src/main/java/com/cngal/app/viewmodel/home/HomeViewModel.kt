package com.cngal.app.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.NewsModel
import com.cngal.app.model.home.UpcomingGameModel
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel()
{
    private val _carousels = MutableStateFlow(ApiResponse.empty<List<CarouselModel>>())
    val carousels = _carousels.asStateFlow()

    private val _news = MutableStateFlow(ApiResponse.empty<List<NewsModel>>())
    val news = _news.asStateFlow()

    private val _weeklyNews = MutableStateFlow(ApiResponse.empty<List<ArticleCardModel>>())
    val weeklyNews = _weeklyNews.asStateFlow()

    private val _upcomingGames = MutableStateFlow(ApiResponse.empty<List<UpcomingGameModel>>())
    val upcomingGames = _upcomingGames.asStateFlow()

    init
    {
        getCarouselData()
        getNewsData()
        getWeeklyNewsData()
        getUpcomingGameData()
    }

    private fun getCarouselData()
    {
        viewModelScope.launch {
            HomeRepository.getCarouselData().onStart {
                _carousels.value = ApiResponse.loading()
            }.catch { e ->
                _carousels.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _carousels.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getNewsData()
    {
        viewModelScope.launch {
            HomeRepository.getNewsData().onStart {
                _news.value = ApiResponse.loading()
            }.catch { e ->
                _news.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _news.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getWeeklyNewsData()
    {
        viewModelScope.launch {
            HomeRepository.getWeeklyNewsData().onStart {
                _weeklyNews.value = ApiResponse.loading()
            }.catch { e ->
                _weeklyNews.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _weeklyNews.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getUpcomingGameData()
    {
        viewModelScope.launch {
            HomeRepository.getUpcomingGameData().onStart {
                _upcomingGames.value = ApiResponse.loading()
            }.catch { e ->
                _upcomingGames.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _upcomingGames.value =
                    ApiResponse.success(response)
            }
        }
    }
}