package com.cngal.app.viewmodel.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.model.square.AnnouncementModel
import com.cngal.app.model.square.FriendLinkModel
import com.cngal.app.model.square.HotTagModel
import com.cngal.app.model.square.LatestCommentModel
import com.cngal.app.model.square.RecentlyEditedGameModel
import com.cngal.app.repository.ExploreRepository
import com.cngal.app.repository.HomeRepository
import com.cngal.app.repository.SquareRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SquareViewModel : ViewModel()
{
    private val _announcements = MutableStateFlow(ApiResponse.empty<List<AnnouncementModel>>())
    val announcements = _announcements.asStateFlow()

    private val _recentlyEditedGames = MutableStateFlow(ApiResponse.empty<List<RecentlyEditedGameModel>>())
    val recentlyEditedGames = _recentlyEditedGames.asStateFlow()

    private val _friendLinks = MutableStateFlow(ApiResponse.empty<List<List<FriendLinkModel>>>())
    val friendLinks = _friendLinks.asStateFlow()

    private val _hotTags = MutableStateFlow(ApiResponse.empty<List<HotTagModel>>())
    val hotTags = _hotTags.asStateFlow()

    private val _latestComments = MutableStateFlow(ApiResponse.empty<List<LatestCommentModel>>())
    val latestComments = _latestComments.asStateFlow()

    init
    {
        getAnnouncementData()
        getRecentlyEditedGameData()
        getFriendLinkData()
        getHotTagData()
        getLatestCommentData()
    }


    private fun getAnnouncementData()
    {
        viewModelScope.launch {
            SquareRepository.getAnnouncementData().onStart {
                _announcements.value = ApiResponse.loading()
            }.catch { e ->
                _announcements.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _announcements.value =
                    ApiResponse.success(model.take(6))
            }
        }
    }

    private fun getRecentlyEditedGameData()
    {
        viewModelScope.launch {
            SquareRepository.getRecentlyEditedGameData().onStart {
                _recentlyEditedGames.value = ApiResponse.loading()
            }.catch { e ->
                _recentlyEditedGames.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _recentlyEditedGames.value =
                    ApiResponse.success( model.take(6) )
            }
        }
    }

    private fun getFriendLinkData()
    {
        viewModelScope.launch {
            SquareRepository.getFriendLinkData().onStart {
                _friendLinks.value = ApiResponse.loading()
            }.catch { e ->
                _friendLinks.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _friendLinks.value =
                    ApiResponse.success(model.shuffled().chunked(model.size / 2) )
            }
        }
    }

    private fun getHotTagData()
    {
        viewModelScope.launch {
            SquareRepository.getHotTagData().onStart {
                _hotTags.value = ApiResponse.loading()
            }.catch { e ->
                _hotTags.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _hotTags.value =
                    ApiResponse.success(response)
            }
        }
    }

    private fun getLatestCommentData()
    {
        viewModelScope.launch {
            SquareRepository.getLatestCommentData().onStart {
                _latestComments.value = ApiResponse.loading()
            }.catch { e ->
                _latestComments.value =
                    ApiResponse.error(e)
            }.collect { response ->
                _latestComments.value =
                    ApiResponse.success(response)
            }
        }
    }
}