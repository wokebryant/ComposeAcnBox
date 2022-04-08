package com.example.composeacornbox.flutter

import com.example.composeacornbox.base.App
import javax.inject.Inject

/**
 * @Author: LuoJia
 * @Date:
 * @Description: Flutter通道, 用于原生和Flutter通信
 */
class MethodChannel @Inject constructor() {

    /**
     *  导航通道
     */
    fun initNavigationChannel(callBack: (Any?) -> Unit) {
        App.flutterEngine.navigationChannel.setMethodCallHandler { call, result ->
            when(call.method) {
                "navigation" -> {
                    val route = call.argument<String>("route")
                    result.success("agree")
                    callBack(route)
                }
            }
        }
    }

    /**
     *  KeyEvent通道
     */
    fun initKeyEventChannel(callBack: (String) -> Unit) {

    }


}