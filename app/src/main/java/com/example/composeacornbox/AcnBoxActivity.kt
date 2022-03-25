package com.example.composeacornbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import com.example.composeacornbox.data.AppState
import com.example.composeacornbox.ui.page.PageNavigation
import com.example.composeacornbox.ui.page.splash.SplashPage
import com.example.composeacornbox.ui.theme.ACNBoxTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置Decor使得view层级获取到Insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ACNBoxTheme { App() }
        }
    }
}

/**
 *  App UI
 */
@Composable
@ExperimentalMaterialApi
private fun App() {
    val (appState, setAppState) = remember {
        mutableStateOf(AppState.Splash)
    }

    when (appState) {
        AppState.Splash -> {
            SplashPage { setAppState(AppState.Home) }
        }

        AppState.Home -> {
            PageNavigation()
        }
    }

}