package com.example.composeacornbox.ui.page.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.WalletCreateState
import com.example.composeacornbox.ui.theme.AcnBlack
import com.example.composeacornbox.ui.widget.DarkButton
import com.example.composeacornbox.ui.widget.DarkStatusBar
import com.example.composeacornbox.utils.RouteUtils
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.delay

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 创建钱包页面
 */

private var startAnim by mutableStateOf(false)
private var finishAnim by mutableStateOf(false)

@Composable
fun WalletCreatePage(navController: NavHostController) {
    DarkStatusBar()
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        val (tip, centerImage, bottomBg, bottomLayout) = createRefs()

        var createState by remember { mutableStateOf(WalletCreateState.Welcome) }

        val transition = updateTransition(
            targetState = startAnim,
            label = "create_wallet"
        )
        val offset by transition.animateDp(
            transitionSpec = { repeatable(7, tween(1000), repeatMode = RepeatMode.Reverse) },
            label = "offset"
        ) {
            when(it) {
                false -> 20.dp
                true -> 0.dp
            }
        }
        val alpha by transition.animateFloat(
            transitionSpec = { repeatable(7, tween(1000), repeatMode = RepeatMode.Reverse)},
            label = "alpha"
        ) {
            when (it) {
                false -> 0f
                true -> 1f
            }
        }

        var title = StringConstant.Empty
        var content = StringConstant.Empty
        var drawable = 0
        var action = StringConstant.Empty
        when(createState) {
            WalletCreateState.Welcome -> {
                title = StringConstant.welcomeTitle
                content = StringConstant.welcomeContent
                drawable = R.drawable.img_create_welcome
                action = StringConstant.welcomeAction
            }
            WalletCreateState.Secure -> {
                title = StringConstant.secureTitle
                content = StringConstant.secureContent
                drawable = R.drawable.img_create_friend
                action = StringConstant.secureAction
            }
            WalletCreateState.Friend -> {
                title = StringConstant.friendTitle
                content = StringConstant.friendContent
                drawable = R.drawable.img_create_secure
                action = StringConstant.friendAction
            }
            WalletCreateState.Explore -> {
                title = StringConstant.exploreTitle
                content = StringConstant.Empty
                drawable = R.drawable.img_create_explore
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 40.dp)
                .constrainAs(tip) {}
                .offset { IntOffset(x = offset.roundToPx(), y = 0) }
                .alpha(alpha)
        ) {
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.W900
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = content,
                fontSize = 18.sp,
                fontWeight = FontWeight.W400,
                color = AcnBlack
            )
        }

        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(centerImage){ top.linkTo(tip.bottom, margin = 60.dp) }
                .offset { IntOffset(x = offset.roundToPx(), y = 0) }
                .alpha(alpha)
        )

        Image(
            painter = painterResource(R.drawable.img_create_green),
            contentDescription = null,
            modifier = Modifier.constrainAs(bottomBg) {
                bottom.linkTo(parent.bottom)
            }
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 30.dp)
                .constrainAs(bottomLayout) {
                bottom.linkTo(parent.bottom)
            },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AnimatedVisibility(!finishAnim) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier.size(40.dp),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.img_create_loading)
                            .crossfade(true)
                            .decoderFactory(GifDecoder.Factory())
                            .build(),
                        contentDescription = null
                    )
                    Text(
                        text = action,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                }
            }
            AnimatedVisibility(finishAnim) {
                DarkButton(
                    content = StringConstant.start,
                    onClick = {
                        RouteUtils.navTo(navController, RouteName.HOME)
                    }
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
        }

        // TODO 固定时长动画,未根据实际加载状态变更
        LaunchedEffect(Unit) {
            delay(200)
            startAnim = true
            delay(2000)
            createState = WalletCreateState.Secure
            delay(2000)
            createState = WalletCreateState.Friend
            delay(2000)
            createState = WalletCreateState.Explore
            delay(1000)
            finishAnim = true
        }
    }

}
