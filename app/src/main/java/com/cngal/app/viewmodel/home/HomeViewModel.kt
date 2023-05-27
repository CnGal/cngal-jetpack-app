package com.cngal.app.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.DiscountGameModel
import com.cngal.app.model.home.FreeGameModel
import com.cngal.app.model.home.LatestArticleModel
import com.cngal.app.model.home.LatestVideoModel
import com.cngal.app.model.home.NewsModel
import com.cngal.app.model.home.PublishedGameModel
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

    private val _news = MutableStateFlow(ApiResponse.empty<List<List<NewsModel>>>())
    val news = _news.asStateFlow()

    private val _weeklyNews = MutableStateFlow(ApiResponse.empty<List<ArticleCardModel>>())
    val weeklyNews = _weeklyNews.asStateFlow()

    private val _upcomingGames = MutableStateFlow(ApiResponse.empty<List<UpcomingGameModel>>())
    val upcomingGames = _upcomingGames.asStateFlow()

    private val _publishedGames = MutableStateFlow(ApiResponse.empty<List<PublishedGameModel>>())
    val publishedGames = _publishedGames.asStateFlow()

    private val _latestArticles = MutableStateFlow(ApiResponse.empty<List<LatestArticleModel>>())
    val latestArticles = _latestArticles.asStateFlow()

    private val _latestVideos = MutableStateFlow(ApiResponse.empty<List<LatestVideoModel>>())
    val latestVideos = _latestVideos.asStateFlow()

    private val _freeGames = MutableStateFlow(ApiResponse.empty<List<FreeGameModel>>())
    val freeGames = _freeGames.asStateFlow()

    private val _discountGames = MutableStateFlow(ApiResponse.empty<List<DiscountGameModel>>())
    val discountGames = _discountGames.asStateFlow()

    init
    {
        getCarouselData()
        getNewsData()
        getWeeklyNewsData()
        getUpcomingGameData()
        getPublishedGameData()
        getLatestArticleData()
        getLatestVideoData()
        getFreeGameData()
        getDiscountGameData()
    }

    private fun getCarouselData()
    {
        viewModelScope.launch {
            HomeRepository.getCarouselData().onStart {
                _carousels.value = ApiResponse.loading()
            }.catch { e ->
                _carousels.value =
                    ApiResponse.error(e)
            }.collect { carousels ->

                //预处理
                carousels.toMutableList().sortByDescending { s -> s.priority }
                val images = carousels.take(3).toMutableList()
                if (carousels.count() >= 6)
                {
                    images.addAll(
                        carousels.filter { s: CarouselModel ->
                            !images.map { it.image }.contains(s.image)
                        }
                            .shuffled()
                            .take(3))
                }
                else
                {
                    images.addAll(carousels.filter { s: CarouselModel ->
                        !images.map { it.image }.contains(s.image)
                    })
                }

                //赋值
                _carousels.value = ApiResponse.success(images)
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
            }.collect { model ->
                _news.value =
                    ApiResponse.success(model.take(12).chunked(3) )
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
            }.collect { model ->
                _weeklyNews.value =
                    ApiResponse.success(model.take(3))
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
            }.collect { model ->
                _upcomingGames.value =
                    ApiResponse.success(model.take(9))
            }
        }
    }

    private fun getPublishedGameData()
    {
        viewModelScope.launch {
            HomeRepository.getPublishedGameData().onStart {
                _publishedGames.value = ApiResponse.loading()
            }.catch { e ->
                _publishedGames.value =
                    ApiResponse.error(e)
            }.collect { model ->
                model.forEach()
                {
                    it.tags=it.tags.shuffled().take(1)
                }

                _publishedGames.value =
                    ApiResponse.success(model.take(9))
            }
        }
    }

    private fun getFreeGameData()
    {
        viewModelScope.launch {
            HomeRepository.getFreeGameData().onStart {
                _freeGames.value = ApiResponse.loading()
            }.catch { e ->
                _freeGames.value =
                    ApiResponse.error(e)
            }.collect { model ->
                model.forEach()
                {
                    it.tags=it.tags.shuffled().take(1)
                }

                _freeGames.value =
                    ApiResponse.success(model.take(9))
            }
        }
    }

    private fun getLatestArticleData()
    {
        viewModelScope.launch {
            HomeRepository.getLatestArticleData().onStart {
                _latestArticles.value = ApiResponse.loading()
            }.catch { e ->
                _latestArticles.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _latestArticles.value =
                    ApiResponse.success(model.take(9))
            }
        }
    }

    private fun getLatestVideoData()
    {
        viewModelScope.launch {
            HomeRepository.getLatestVideoData().onStart {
                _latestVideos.value = ApiResponse.loading()
            }.catch { e ->
                _latestVideos.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _latestVideos.value =
                    ApiResponse.success(model.take(9))
            }
        }
    }

    private fun getDiscountGameData()
    {
        viewModelScope.launch {
            HomeRepository.getDiscountGameData().onStart {
                _discountGames.value = ApiResponse.loading()
            }.catch { e ->
                _discountGames.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _discountGames.value =
                    ApiResponse.success(model.take(9))
            }
        }
    }

}