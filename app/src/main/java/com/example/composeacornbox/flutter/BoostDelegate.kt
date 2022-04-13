package com.example.composeacornbox.flutter

import android.content.Intent
import android.util.Log
import com.example.composeacornbox.activity.WBRecordActivity
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 闲鱼FlutterBoostDelegate
 */

val boostDelegate = object : FlutterBoostDelegate {
    /** 处理Native Route */
    override fun pushNativeRoute(options: FlutterBoostRouteOptions?) {
        Log.i("FlutterBoostDelegate", "pushNativeRoute")
        options?.let {
            val targetActivity = when (it.pageName()) {
                "native_wb_record_page" -> WBRecordActivity::class.java
                else -> null
            }
            val currentActivity = FlutterBoost.instance().currentActivity()
            val intent = Intent(currentActivity, targetActivity)
            currentActivity.startActivityForResult(intent, it.requestCode())
        }
    }

    /** 处理Flutter Route */
    override fun pushFlutterRoute(options: FlutterBoostRouteOptions?) {
        Log.i("FlutterBoostDelegate", "pushFlutterRoute")
        options?.let {
            val currentActivity = FlutterBoost.instance().currentActivity()
            val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .destroyEngineWithActivity(false)
                .uniqueId(it.uniqueId())
                .url(it.pageName())
                .urlParams(it.arguments())
                .build(currentActivity)
            currentActivity.startActivity(intent)
        }
    }

}