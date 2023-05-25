package com.cngal.app.model.shared

import com.cngal.app.model.home.CarouselModel

class ApiResponse<T>(
    var code: Int = 0,
    var msg: String = "",
    var data: T ?,
    var state: AppState=AppState.LOADING
)
{
    companion object
    {
        fun<T> empty() = ApiResponse<T>(0, "", null, AppState.EMPTY)

        fun<T> loading() = ApiResponse<T>(0, "", null, AppState.LOADING)

        fun<T> success(re:T) = ApiResponse<T>(200, "", re, AppState.SUCCESS)

        fun<T> error(e:Throwable) = ApiResponse<T>(404, e.message?:"", null, AppState.ERROR)
    }
}

//记录接口状态的枚举
enum class AppState {
    LOADING, SUCCESS, ERROR, EMPTY
}
