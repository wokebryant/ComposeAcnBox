package com.example.composeacornbox.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.example.composeacornbox.ui.page.workbox.WorkBoxExtractSalaryPage
import com.example.composeacornbox.ui.theme.ACNBoxTheme

/**
 * @Author: LuoJia
 * @Date:
 * @Description: Flutter测试页面
 */
class FlutterJumpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 设置Decor使得view层级获取到Insets
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ACNBoxTheme { FlutterApp() }
        }
    }

    @Preview
    @Composable
    fun FlutterApp() {
        WorkBoxExtractSalaryPage(null)
    }
}