package com.cngal.app.viewmodel.home

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.repository.ExploreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SquareViewModel : ViewModel()
{
    private val _list = MutableStateFlow(SnapshotStateList<Int>())
    val list = _list.asStateFlow()

    private var index:Int=10;

    fun add()
    {


        viewModelScope.launch {
            ExploreRepository.getEvaluationData().onStart {

            }.catch { e ->

            }.collect { response ->
                _list.update {
                    index++
                    it.add(index)
                    return@collect
                }
            }
        }
    }
}