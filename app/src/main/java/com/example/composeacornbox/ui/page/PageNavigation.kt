package com.example.composeacornbox.ui.page

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.ui.page.home.HomePage
import com.example.composeacornbox.ui.page.login.LoginPage
import com.example.composeacornbox.ui.page.login.WalletCreatePage
import com.example.composeacornbox.ui.page.login.WalletImportPage
import com.example.composeacornbox.ui.page.recommend.RecommendPage
import com.example.composeacornbox.ui.page.webview.WebViewPage
import com.example.composeacornbox.ui.page.workbox.WorkBoxExtractSalaryPage
import com.example.composeacornbox.ui.page.workbox.WorkBoxPage
import com.example.composeacornbox.ui.page.workbox.WorkBoxWelcomePage
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 页面导航
 */

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun PageNavigation() {
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val startDestination = RouteName.LOGIN

    AnimatedNavHost(
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
        composable(
            route = RouteName.HOME,
            enterTransition = {
                // 动画也可根据路由来源和去向自定义
                when(initialState.destination.route) {
                    RouteName.WORK_BOX_WELCOME -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(500)
                    )
                    else -> slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(500)
                    )
                }
            },
            exitTransition = {
                null
            }
        ) {
            HomePage(navController)
        }
        composable(
            route = RouteName.WORK_BOX,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            }
        ) {
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
        composable(route = RouteName.WORK_BOX_SALARY_RECORD) {
            WorkBoxExtractSalaryPage(navController)
        }
    }
}