package com.example.composeacornbox.base

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.composeacornbox.constant.FlutterEngineId
import com.example.composeacornbox.constant.FlutterInitRoute
import dagger.hilt.android.HiltAndroidApp
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

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
        lateinit var flutterEngine: FlutterEngine
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this

        initFlutterEngine()
    }

    /**
     *  初始化Flutter引擎
     *  加速原生页面跳转FlutterActivity
     */
    private fun initFlutterEngine() {
        // Flutter引擎实例
        flutterEngine = FlutterEngine(this)
        // 设置启动路由
        flutterEngine.navigationChannel.setInitialRoute(FlutterInitRoute)
        // 预加载引擎
        flutterEngine.dartExecutor.executeDartEntrypoint(
            // 会执行Dart入口方法
            DartExecutor.DartEntrypoint.createDefault()
        )
        // 缓存Flutter引擎
        FlutterEngineCache
            .getInstance()
            .put(FlutterEngineId, flutterEngine)
    }

}