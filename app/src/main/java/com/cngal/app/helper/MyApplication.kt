package com.cngal.app.helper


import android.app.Application

/**
 * 自定义 Application
 */
class MyApplication : Application() {

    companion object {
        lateinit var instance: Application
    }

    init {
        instance = this
    }

}
