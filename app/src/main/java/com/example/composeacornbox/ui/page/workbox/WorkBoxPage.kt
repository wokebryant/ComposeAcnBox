package com.example.composeacornbox.ui.page.workbox

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeacornbox.activity.BaseFlutterActivity
import com.example.composeacornbox.constant.FlutterEngineId
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.ui.page.usercenter.WorkBoxViewModel
import com.example.composeacornbox.ui.theme.WorkBoxBg
import com.example.composeacornbox.ui.widget.DarkStatusBar
import com.example.composeacornbox.utils.RouteUtils.back
import com.google.accompanist.insets.statusBarsPadding
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs


/**
 * @Author: LuoJia
 * @Date:
 * @Description: WorkBox页面
 */

@Composable
fun WorkBoxPage(
    navController: NavHostController,
    viewModel: WorkBoxViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    DarkStatusBar()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WorkBoxBg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding()
        ) {
            WBHeader (
                title = StringConstant.workBox,
                isWorkBox = true,
                onClose = { navController.back() },
                onClick = {
                    jumpToTaskFlutterActivity(context)
//                    jumpToTaskFlutterActivityByBoost(context)
                }
            )
            WBBody(viewModel.viewStates)
        }
    }
}

/**
 *  通过原生跳转
 */
fun jumpToTaskFlutterActivity(context: Context) {
    with(context) {
        startActivity(
            FlutterActivity
                .CachedEngineIntentBuilder(BaseFlutterActivity::class.java, FlutterEngineId)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                .build(this)
        )
    }
}

/**
 *  通过FlutterBoost跳转
 */
fun jumpToTaskFlutterActivityByBoost(context: Context) {
    val options = FlutterBoostRouteOptions.Builder()
        .pageName("wb_personal_page")
        .arguments(HashMap())
        .requestCode(1111)
        .build()
    FlutterBoost.instance().open(options)
}

