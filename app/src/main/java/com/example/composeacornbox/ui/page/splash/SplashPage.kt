package com.example.composeacornbox.ui.page.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeacornbox.ui.theme.splashAssets
import kotlinx.coroutines.delay

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 闪屏页
 */

@Composable
fun SplashPage(onSplashCompleted: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary),
        contentAlignment = Alignment.TopCenter
    ) {
        LaunchedEffect(Unit) {
            delay(1000)
            onSplashCompleted()
        }
        Image(
            painter = painterResource(MaterialTheme.splashAssets.background),
            contentDescription = "splash bg",
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(MaterialTheme.splashAssets.logo),
            contentDescription = "splash log",
            modifier = Modifier.padding(top = 200.dp).size(65.dp)
        )
    }
}