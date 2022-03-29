package com.example.composeacornbox.ui.page.webview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.composeacornbox.ui.theme.AcnGreen
import com.example.composeacornbox.utils.RouteUtils.back
import com.google.accompanist.web.LoadingState
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

/**
 * @Author: LuoJia
 * @Date:
 * @Description: WebView
 */

@Composable
fun WebViewPage(navController: NavHostController, url: String = "https://www.baidu.com/") {
    val state = rememberWebViewState(url)

    Column(
        modifier = Modifier.statusBarsPadding()
    ) {
        TopAppBar(
            backgroundColor = Color.White,
            title = { Text(text = "") },
            elevation = 0.dp,
            navigationIcon = {
                IconButton(onClick = { navController.back()}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )

        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                color = AcnGreen
            )
        }

        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            }
        )
    }
}
