package com.cngal.app.viewmodel.periphery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cngal.app.model.entry.InformationContentModel
import com.cngal.app.model.periphery.PeripheryModel
import com.cngal.app.model.periphery.PeripheryType
import com.cngal.app.model.shared.ApiResponse
import com.cngal.app.repository.PeripheryRepository
import com.cngal.app.uistate.periphery.SinglePeripheryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SinglePeripheryViewModel : ViewModel()
{
    private val _periphery = MutableStateFlow(ApiResponse.empty<PeripheryModel>())
    val periphery = _periphery.asStateFlow()

    private val _uiState = MutableStateFlow(SinglePeripheryUiState())
    val uiState = _uiState.asStateFlow()

    fun loadPeripheryData(id: Long?)
    {
        if (id == null || id <= 0)
        {
            return
        }

        viewModelScope.launch {
            PeripheryRepository.getPeripheryData(id).onStart {
                _periphery.value = ApiResponse.loading()
            }.catch { e ->
                _periphery.value =
                    ApiResponse.error(e)
            }.collect { model ->
                _uiState.update { state ->
                    //合并相册
                    state.images.clear()
                    model.pictures.forEach {
                        state.images.addAll(it.content)
                    }
                    //标签
                    state.tags.clear()
                    state.tags.add(model.type.toString())
                    if(!model.category.isNullOrBlank())
                    {
                        state.tags.add(model.category)
                    }
                    if(model.isReprint)
                    {
                        state.tags.add("已再版")
                    }
                    else
                    {
                        state.tags.add("未再版")
                    }
                    if(model.isAvailableItem)
                    {
                        state.tags.add("装饰品")
                    }


                    //基础信息
                    state.information.clear()
                    if(!model.size.isNullOrBlank())
                    {
                        state.information.add(InformationContentModel("尺寸",model.size.toString()))
                    }
                    if(!model.brand.isNullOrBlank())
                    {
                        state.information.add(InformationContentModel("品牌",model.brand.toString()))
                    }
                    if(!model.author.isNullOrBlank())
                    {
                        state.information.add(InformationContentModel("作者",model.author.toString()))
                    }
                    if(!model.material.isNullOrBlank())
                    {
                        state.information.add(InformationContentModel("材质",model.material.toString()))
                    }
                    if(!model.individualParts.isNullOrBlank())
                    {
                        state.information.add(InformationContentModel("单独部件数量",model.individualParts.toString()))
                    }
                    if(!model.price.isNullOrBlank())
                    {
                        state.information.add(InformationContentModel("价格",model.price.toString()))
                    }
                    if(model.type==PeripheryType.SetorAlbumEtc)
                    {
                        if(model.pageCount!=0)
                        {
                            state.information.add(InformationContentModel("页数",model.pageCount.toString()))
                        }
                    }
                    if(model.type==PeripheryType.Ost)
                    {
                        if(model.songCount!=0)
                        {
                            state.information.add(InformationContentModel("歌曲数",model.songCount.toString()))
                        }
                    }
                    //分享
                    state.link = "https://www.cngal.org/peripheries/index/${model.id}"
                    state.shareText =
                        "【周边】 ${model.name} | CnGal资料站 https://www.cngal.org/peripheries/index/${model.id}"

                    state
                }
                _periphery.update {
                    ApiResponse.success(model)
                }
            }
        }
    }

}