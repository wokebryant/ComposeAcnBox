package com.example.composeacornbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import com.example.composeacornbox.base.App
import com.example.composeacornbox.constant.FlutterEngineId
import com.example.composeacornbox.data.AppState
import com.example.composeacornbox.ui.page.PageNavigation
import com.example.composeacornbox.ui.page.splash.SplashPage
import com.example.composeacornbox.ui.theme.ACNBoxTheme
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.engine.FlutterEngineCache

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置Decor使得view层级获取到Insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ACNBoxTheme { JaApp() }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        releaseFlutterEngine()
    }

    /**
     *  释放Flutter引擎
     */
    private fun releaseFlutterEngine() {
        FlutterEngineCache.getInstance().remove(FlutterEngineId)
        App.flutterEngine.destroy()
    }
}

/**
 *  App UI
 */
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
@ExperimentalMaterialApi
private fun JaApp() {
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