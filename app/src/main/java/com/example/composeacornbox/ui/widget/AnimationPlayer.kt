package com.example.composeacornbox.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 动画播放
 */

@Composable
fun LottieAnimationPlayer(
    modifier: Modifier,
    assetRes: String,
    iterations: Int = 1,
    contentScale: ContentScale = ContentScale.Fit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(assetRes))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = iterations
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = progress,
        contentScale = contentScale,

    )
}