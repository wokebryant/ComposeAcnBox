package com.example.composeacornbox

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.core.view.WindowCompat
import com.example.composeacornbox.base.BaseActivity
import com.example.composeacornbox.ui.page.home.HomePage
import com.example.composeacornbox.ui.page.splash.SplashPage
import com.example.composeacornbox.ui.theme.ACNBoxTheme

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置Decor使得view层级获取到Insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ACNBoxTheme { App() }
        }
    }
}

enum class AppState {
    Splash,
    Home
}

/**
 *  App UI
 */
@Composable
private fun App() {
    val (appState, setAppState) = remember {
        mutableStateOf(AppState.Splash)
    }

    when (appState) {
        AppState.Splash -> {
            SplashPage { setAppState(AppState.Home) }
        }

        AppState.Home -> {
            HomePage()
        }
    }

}