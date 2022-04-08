package com.example.composeacornbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeacornbox.ui.theme.ACNBoxTheme
import com.example.composeacornbox.ui.widget.DarkStatusBar

/**
 * @Author: LuoJia
 * @Date:
 * @Description: Flutter测试页面
 */
class TestFlutterActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ACNBoxTheme { FlutterApp() }
        }
    }

    @Preview
    @Composable
    fun FlutterApp() {
        DarkStatusBar()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                modifier = Modifier,
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White
                )
            ) {
                Text("这是Flutter测试页面")
            }
        }
    }
}