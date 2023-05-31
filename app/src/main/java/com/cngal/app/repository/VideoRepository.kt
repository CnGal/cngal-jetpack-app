package com.cngal.app.repository

import com.cngal.app.model.tag.TagModel
import com.cngal.app.model.video.VideoModel
import com.cngal.app.service.TagService
import com.cngal.app.service.VideoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class VideoRepository
{
    companion object
    {
        fun getVideoData(id: Long): Flow<VideoModel> = flow {
            val data = VideoService.instance.getVideoData(id)
            emit(data)
        }.flowOn(Dispatchers.IO)
    }
}