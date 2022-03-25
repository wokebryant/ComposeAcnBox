package com.example.composeacornbox.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@Composable
fun DarkStatusBar() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent, useDarkIcons)
    }
}

@Composable
fun LightStatusBar() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false
    SideEffect {
        systemUiController.setStatusBarColor(Color.Transparent, useDarkIcons)
    }
}