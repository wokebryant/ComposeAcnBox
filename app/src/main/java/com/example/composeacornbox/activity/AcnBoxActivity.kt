package com.example.composeacornbox.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.composeacornbox.base.App
import com.example.composeacornbox.constant.FlutterEngineId
import com.example.composeacornbox.data.AppState
import com.example.composeacornbox.ui.page.PageNavigation
import com.example.composeacornbox.ui.page.splash.SplashPage
import com.example.composeacornbox.ui.theme.ACNBoxTheme
import dagger.hilt.android.AndroidEntryPoint
import io.flutter.embedding.engine.FlutterEngineCache
import kotlinx.coroutines.launch

@AndroidEntryPoint
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    private val viewModel: AcnBoxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置Decor使得view层级获取到Insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        observeFlutterChannel()
        setContent {
            ACNBoxTheme { JaApp() }
        }
    }

    private fun observeFlutterChannel() {
        lifecycleScope.launch {
            viewModel.channelEvent.flowWithLifecycle(lifecycle).collect { event ->
                when (event) {
                    is ChannelEvent.NavigationEvent -> flutterJumpToNative()
                    is ChannelEvent.KeyEvent -> {}
                }
            }
        }
        viewModel.dispatchAction(ChannelAction.ObserverNavigationChannel)
    }

    private fun flutterJumpToNative() {
        startActivity(Intent(this, FlutterJumpActivity::class.java))
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