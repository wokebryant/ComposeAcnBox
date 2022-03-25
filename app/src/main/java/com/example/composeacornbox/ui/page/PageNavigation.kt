package com.example.composeacornbox.ui.page

import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.ui.page.home.HomePage
import com.example.composeacornbox.ui.page.login.LoginPage
import com.example.composeacornbox.ui.page.login.WalletCreatePage
import com.example.composeacornbox.ui.page.login.WalletImportPage
import com.example.composeacornbox.ui.page.recommend.RecommendPage
import com.example.composeacornbox.ui.page.webview.WebViewPage
import com.example.composeacornbox.ui.page.workbox.WorkBoxPage
import com.example.composeacornbox.ui.page.workbox.WorkBoxWelcomePage

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 页面导航
 */

@ExperimentalMaterialApi
@Composable
fun PageNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val startDestination = RouteName.LOGIN

    // TODO 导航自定义跳转动画
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.background(Color.White)
    ) {
        composable(route = RouteName.LOGIN) {
            LoginPage(navController)
        }
        composable(route = RouteName.CREATE_WALLET) {
            WalletCreatePage(navController)
        }
        composable(route = RouteName.IMPORT_WALLET) {
            WalletImportPage(navController)
        }
        composable(route = RouteName.HOME) {
            HomePage(navController)
        }
        composable(route = RouteName.WORK_BOX) {
            WorkBoxPage(navController)
        }
        composable(route = RouteName.WORK_BOX_WELCOME) {
            WorkBoxWelcomePage(navController)
        }
        composable(route = RouteName.RECOMMEND) {
            RecommendPage(navController)
        }
        composable(route = RouteName.WEB_VIEW) {
            WebViewPage(navController)
        }
    }
}