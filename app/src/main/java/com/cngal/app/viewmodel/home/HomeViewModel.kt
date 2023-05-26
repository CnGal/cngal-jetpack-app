package com.cngal.app.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.article.ArticleCardModel
import com.cngal.app.model.explore.PersonalRecommendModel
import com.cngal.app.model.home.AnnouncementModel
import com.cngal.app.model.home.CarouselModel
import com.cngal.app.model.home.FriendLinkModel
import com.cngal.app.model.home.LatestArticleModel
import com.cngal.app.model.home.LatestVideoModel
import com.cngal.app.model.home.NewsModel
import com.cngal.app.model.home.PublishedGameModel
import com.cngal.app.model.home.RecentlyEditedGameModel
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

    private val _publishedGames = MutableStateFlow(ApiResponse.empty<List<PublishedGameModel>>())
    val publishedGames = _publishedGames.asStateFlow()

    private val _latestArticles = MutableStateFlow(ApiResponse.empty<List<LatestArticleModel>>())
    val latestArticles = _latestArticles.asStateFlow()

    private val _latestVideos = MutableStateFlow(ApiResponse.empty<List<LatestVideoModel>>())
    val latestVideos = _latestVideos.asStateFlow()

    private val _announcements = MutableStateFlow(ApiResponse.empty<List<AnnouncementModel>>())
    val announcements = _announcements.asStateFlow()

    private val _recentlyEditedGames = MutableStateFlow(ApiResponse.empty<List<RecentlyEditedGameModel>>())
    val recentlyEditedGames = _recentlyEditedGames.asStateFlow()

    private val _friendLinks = MutableStateFlow(ApiResponse.empty<List<FriendLinkModel>>())
    val friendLinks = _friendLinks.asStateFlow()



    init
    {
        getCarouselData()
        getNewsData()
        getWeeklyNewsData()
        getUpcomingGameData()
        getPublishedGameData()
        getLatestArticleData()
        getLatestVideoData()
        getAnnouncementData()
        getRecentlyEditedGameData()
        getFriendLinkData()
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

    private fun getPublishedGameData()
    {
        viewModelScope.launch {
            HomeRepository.getPublishedGameData().onStart {
                _publishedGames.value = ApiResponse.loading()
            }.catch { e ->
                _publishedGames.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _publishedGames.value =
                    ApiResponse.success(response)
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
            }.collect { response ->
                _latestArticles.value =
                    ApiResponse.success(response)
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
            }.collect { response ->
                _latestVideos.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getAnnouncementData()
    {
        viewModelScope.launch {
            HomeRepository.getAnnouncementData().onStart {
                _announcements.value = ApiResponse.loading()
            }.catch { e ->
                _announcements.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _announcements.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getRecentlyEditedGameData()
    {
        viewModelScope.launch {
            HomeRepository.getRecentlyEditedGameData().onStart {
                _recentlyEditedGames.value = ApiResponse.loading()
            }.catch { e ->
                _recentlyEditedGames.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _recentlyEditedGames.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getFriendLinkData()
    {
        viewModelScope.launch {
            HomeRepository.getFriendLinkData().onStart {
                _friendLinks.value = ApiResponse.loading()
            }.catch { e ->
                _friendLinks.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _friendLinks.value =
                    ApiResponse.success(response)
            }
        }
    }
}