package com.example.composeacornbox.ui.page.workbox

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.AnimState
import com.example.composeacornbox.ui.theme.WorkBoxBg
import com.example.composeacornbox.ui.theme.WorkBoxHint
import com.example.composeacornbox.ui.widget.DarkStatusBar
import com.example.composeacornbox.ui.widget.LottieAnimationPlayer
import com.example.composeacornbox.ui.widget.OrangeButton
import com.example.composeacornbox.utils.RouteUtils
import com.example.composeacornbox.utils.RouteUtils.back
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay

/**
 * @Author: LuoJia
 * @Date:
 * @Description: WorkBox欢迎页面
 */

@Composable
fun WorkBoxWelcomePage(navController: NavHostController) {
    DarkStatusBar()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WorkBoxBg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding()
        ) {
            WBHeader(
                onlyClose = true,
                onClose = { navController.back() }
            )
            WBAnimationLoader(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 140.dp)
                    .weight(1f)
            )
        }
        WBWelocomeBottomLayout(navController)
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun WBAnimationLoader(modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LottieAnimationPlayer(
            modifier = Modifier.fillMaxWidth(),
            assetRes = StringConstant.welcome1AnimationRes
        )
        LottieAnimationPlayer(
            modifier = Modifier.fillMaxWidth(),
            assetRes = StringConstant.welcome2AnimationRes
        )
    }
}

@Composable
private fun WBWelocomeBottomLayout(navController: NavHostController) {
    var animaState by remember {
        mutableStateOf(AnimState.Stop)
    }
    val initOffsetY = with(LocalDensity.current) { - 50.dp.roundToPx() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AnimatedVisibility(
            visible = animaState != AnimState.Stop,
            enter = slideInVertically(
                initialOffsetY = { initOffsetY },
                animationSpec = tween(1000)
            ) + fadeIn(
                animationSpec = tween(1000)
            )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = StringConstant.welcomeWB,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.size(10.dp))

                Text(
                    text = StringConstant.welcomeWB_Benefit,
                    color = WorkBoxHint,
                    fontWeight = FontWeight.W300,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.size(20.dp))
                OrangeButton(
                    content = StringConstant.joinNow,
                    onClick = {
                        RouteUtils.navTo(navController, RouteName.WORK_BOX)
                    }
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        delay(500)
        animaState = AnimState.Start
    }
}