package com.example.composeacornbox.utils

import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.view.WindowManager
import com.example.composeacornbox.base.App

object SizeUtils {
    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     *  获取屏幕尺寸
     *  getSize: 不包含状态栏，导航栏
     */
    fun getScreenSize(): Point {
        val context = App.CONTEXT
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)

        return point
    }

    /**
     *  获取屏幕尺寸
     *  getRealSize: 包含状态栏，导航栏
     */
    fun getRealScreenSize(): Point {
        val context = App.CONTEXT
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val point = Point()
        display.getRealSize(point)

        return point
    }

    /**
     *  获取状态栏高度
     */
    fun getStatusBarHeight(): Int {
        val context = App.CONTEXT
        val resources: Resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")

        return resources.getDimensionPixelSize(resourceId)
    }

    /**
     *  获取导航栏高度
     */
    fun getNavBarHeight(): Int {
        val context = App.CONTEXT
        val res: Resources = context.resources
        val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")

        return if (resourceId != 0) {
            res.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }
}