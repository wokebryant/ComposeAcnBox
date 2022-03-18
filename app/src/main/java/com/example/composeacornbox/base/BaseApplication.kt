package com.example.composeacornbox.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author: LuoJia
 * @Date: Application基类
 * @Description:
 */
@HiltAndroidApp
class BaseApplication : Application()  {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this
    }
}