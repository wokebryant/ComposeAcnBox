package com.example.composeacornbox.ui.page.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.*
import com.example.composeacornbox.ui.theme.*
import com.example.composeacornbox.ui.widget.HomeStickyListView
import com.example.composeacornbox.utils.SizeUtils
import com.google.accompanist.insets.statusBarsPadding

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 首页组件
 */

@Composable
fun Header(
    navController: NavHostController,
    avatarState: SwitchState,
    viewState: HomeViewState,
    onHeaderStateChange: (HeaderState, SwitchState) -> Unit,
) {
    var accountState = viewState.accountState[0]
    var eyeState: SwitchState by remember {
        mutableStateOf(SwitchState.Open)
    }
    var addState: SwitchState by remember {
        mutableStateOf(SwitchState.Close)
    }

    val padding = when(avatarState) {
        SwitchState.Open -> 0.dp
        SwitchState.Close -> 10.dp
    }
    val headerPadding by animateDpAsState(
        targetValue = padding,
        animationSpec = tween(500)
    )

    val height: Dp = when(eyeState) {
        SwitchState.Open -> headerExpandHeight
        SwitchState.Close -> headerCollapseHeight
    }
    val surfaceHeight by animateDpAsState(targetValue = height)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(surfaceHeight)
            .padding(horizontal = headerPadding)
            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp)),
    ) {
        Image(
            painter = painterResource(MaterialTheme.splashAssets.background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize().statusBarsPadding()
                .padding(start = 30.dp, end = 20.dp, top = 10.dp, bottom = 30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = StringConstant.ACN_Balance,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.size(10.dp))
                Image(
                    painter = painterResource(
                        when(eyeState) {
                            SwitchState.Open -> R.drawable.ic_eye_visible_white
                            SwitchState.Close -> R.drawable.ic_eye_invisible_white
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            eyeState = eyeState.reverse()
                            onHeaderStateChange(HeaderState.Eye, eyeState)
                        },
                )
                Spacer(modifier = Modifier.weight(1f))
                AsyncImage(
                    model = accountState.avatarUrl,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .clickable {
                            onHeaderStateChange(HeaderState.Account, SwitchState.Open)
                        }
                )
                Spacer(modifier = Modifier.size(15.dp))
                Image(
                    painter = painterResource(R.drawable.ic_plus_white),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            addState = addState.reverse()
                            onHeaderStateChange(HeaderState.Add, addState)
                        }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = when(eyeState) {
                    SwitchState.Open -> true
                    SwitchState.Close -> false
                }
            ) {
                Column {
                    Text(
                        text = accountState.acnCoinNum.toString(),
                        color = Color.White,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.W600,
                    )
                    Text(
                        text = accountState.rmbNum.toString(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                    )
                }
            }
        }
    }
}

@Composable
fun KycCard(animState: AnimState, onSwitchStateChange: (SwitchState) -> Unit) {
    val screenWith = SizeUtils.getScreenSize().x
    val targetValue = when(animState) {
        AnimState.Start -> 0
        AnimState.Stop -> screenWith
    }
    val offset by animateIntAsState(
        targetValue = targetValue,
        animationSpec = tween(500)
    )
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(kycCardHeight)
            .padding(10.dp)
            .offset {
                IntOffset(offset, 0)
            }
            .clickable {
                onSwitchStateChange(SwitchState.Open)
            },
        color = WorkBoxPink,
        shape = RoundedCornerShape(40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.img_illustration_task),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = StringConstant.kycCardTitle,
                    fontSize = 24.sp,
                    color = WorkBoxBrown,
                    fontWeight = FontWeight.W600
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.ic_next_black),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
            }
            Spacer(modifier = Modifier.size(15.dp))
            Text(
                text = StringConstant.kycCardContent,
                color = WorkBoxBrown,
                fontSize = 14.sp
            )
        }
    }
}

var bottomHeight by mutableStateOf(bottomSliderCollapseHeight.toFloat())

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomSlider(
    navController: NavHostController,
    viewState: HomeViewState
) {
    val nestedScrollConnection = remember(bottomHeight) {
        BottomSliderNestedScrollConnection(bottomHeight)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(with(LocalDensity.current) { bottomHeight.toDp() })
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
            .padding(top = 10.dp)
            .nestedScroll(nestedScrollConnection)

    ) {
        HomeStickyListView(navController, viewState)
    }
}

/**
 *  处理嵌套滑动
 */
private class BottomSliderNestedScrollConnection(val height: Float) : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        return if (source == NestedScrollSource.Drag && available.y < 0) {
            if (height < bottomSlideExpandHeight) {
                bottomHeight -= available.y
                Offset(x = 0f, y = available.y)
            } else {
                Offset.Zero
            }
        } else {
            Offset.Zero
        }
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        return if (source == NestedScrollSource.Drag && available.y > 0) {
            if (height >= bottomSliderCollapseHeight) {
                bottomHeight -= available.y
                Offset(x = 0f, y = available.y)
            } else {
                Offset.Zero
            }
        } else {
            Offset.Zero
        }
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        return super.onPreFling(available)
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        return super.onPostFling(consumed, available)
    }

}