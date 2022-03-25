package com.example.composeacornbox.ui.page.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieConstants
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.ui.widget.DarkButton
import com.example.composeacornbox.ui.widget.LottieAnimationPlayer
import com.example.composeacornbox.ui.widget.TransButton
import com.example.composeacornbox.utils.RouteUtils

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 登陆页面组件
 */

/**
 *  Lottie动画加载
 */
@Composable
fun AnimationLoader() {
    LottieAnimationPlayer(
        modifier = Modifier.fillMaxSize().padding(bottom = 150.dp),
        assetRes = StringConstant.loginAnimationRes,
        contentScale = ContentScale.Crop,
        iterations = LottieConstants.IterateForever
    )
}

/**
 * 布局
 */
@Composable
fun LoginLayout(navController: NavHostController) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (topBg, bottomBg, log, account) = createRefs()

        Image(
            painter = painterResource(R.drawable.img_create_green),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(bottomBg) { bottom.linkTo(parent.bottom) }
        )
        Image(
            painter = painterResource(R.drawable.img_acn_box),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 40.dp, top = 150.dp).size(width = 100.dp, height = 80.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .constrainAs(account) { bottom.linkTo(parent.bottom) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val annotatedText = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.White,
                    )
                ) {
                    append(StringConstant.loginProtocolDes)
                }

                pushStringAnnotation(
                    tag = "desc",
                    annotation = StringConstant.loginProtocolClickSpan
                )
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        textDecoration = TextDecoration.Underline
                    )
                ) {
                    append(StringConstant.loginProtocolClickSpan)
                }
                pop()
            }
            ClickableText(
                text = annotatedText,
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "desc",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {
                        RouteUtils.navTo(navController, RouteName.WEB_VIEW)
                    }
                },
            )
            Spacer(modifier = Modifier.size(10.dp))
            DarkButton(
                content = StringConstant.createAccount,
                onClick = {
                    RouteUtils.navTo(navController, RouteName.CREATE_WALLET)
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            TransButton(
                content = StringConstant.importAccount,
                onClick = {
//                    RouteUtils.navTo(navController, RouteName.IMPORT_WALLET)
                }
            )
        }
    }
}