package com.example.composeacornbox.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * @Author: LuoJia
 * @Date:
 * @Description: Application基类
 */
@HiltAndroidApp
class App : Application()  {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var CONTEXT: Context
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this
    }
}