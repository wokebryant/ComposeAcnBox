package com.example.composeacornbox

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.composeacornbox.base.App
import com.example.composeacornbox.constant.FlutterEngineId
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.constant.StringConstant
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
        val intent = Intent(this, TestFlutterActivity::class.java)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.channelEvent.collect { event ->
                    when (event) {
                        is ChannelEvent.NavigationEvent -> {
                            startActivity(intent)
                        }
                        is ChannelEvent.KeyEvent -> {

                        }
                    }
                }
            }
        }
        viewModel.dispatchAction(ChannelAction.ObserverNavigationChannel)
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

    @SuppressLint("RememberReturnType")
    @Composable
    fun ObserveFlutterChannel() {
        val lifecycleOwner = LocalLifecycleOwner.current
        val flow = viewModel.channelEvent
        val flowAware = remember(flow, lifecycleOwner) {
            flow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
        }
        val event = flowAware.collectAsState(ChannelEvent.NavigationEvent(""))
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

@Composable
private fun Navigation(route: String) {
    val context = LocalContext.current
    when (route) {
        RouteName.WORK_BOX_SALARY_RECORD -> {
            with(context) {
                startActivity(Intent(this, TestFlutterActivity::class.java))
            }
        }
    }
}