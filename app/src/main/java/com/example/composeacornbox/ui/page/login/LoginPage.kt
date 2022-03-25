package com.example.composeacornbox.ui.page.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.composeacornbox.ui.widget.DarkStatusBar


/**
 * @Author: LuoJia
 * @Date:
 * @Description: 登录页面
 */

@Composable
fun LoginPage(navController: NavHostController) {
    DarkStatusBar()
    AnimationLoader()
    LoginLayout(navController)
}

