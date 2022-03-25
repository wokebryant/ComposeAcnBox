package com.example.composeacornbox.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import com.example.composeacornbox.R
import com.example.composeacornbox.ui.widget.LightStatusBar
import com.google.accompanist.insets.ProvideWindowInsets

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

/**
 *  设置Splash资源
 */
@Stable
class SplashAssets(
    background: Int,
    logo: Int
) {
    var background by mutableStateOf(background)
        internal set
    var logo by mutableStateOf(logo)
        internal set
}

private val LightSplashAssets = SplashAssets(
    background = R.drawable.img_splash_bg,
    logo = R.drawable.ic_logo_white
)

private val DarkSplashAssets = SplashAssets(
    background = 0,
    logo = 0
)

/** 设置全局引用(Static) */
internal var LocalSplashAssets = staticCompositionLocalOf { LightSplashAssets as SplashAssets }

/** 将资源暴露, 可以通过MaterialTheme.splashAssets引用 */
val MaterialTheme.splashAssets
    @Composable
    @ReadOnlyComposable
    get() = LocalSplashAssets.current


@Composable
fun ACNBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val splashAssets = if (darkTheme) {
        DarkSplashAssets
    } else {
        LightSplashAssets
    }

    // 设置状态栏属性
    LightStatusBar()

    // 提供WindowInsets,方便控制状态栏,导航栏,键盘弹起区域等系统窗口
    ProvideWindowInsets {
        CompositionLocalProvider(
            LocalSplashAssets provides splashAssets,
        ) {
            MaterialTheme(
                colors = colors,
                typography = Typography,
                shapes = Shapes,
                content = content
            )
        }
    }

}