package com.example.composeacornbox.ui.page.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.ACN_Balance
import com.example.composeacornbox.ui.theme.splashAssets
import com.example.composeacornbox.ui.widget.StickyListView

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@Composable
fun HomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Header()
            KYCCard()
        }
        BottomSlider()
    }
}

sealed class SwitchState {
    object OPEN: SwitchState()
    object CLOSE: SwitchState()
}

@Composable
private fun Header() {
    var eyeState: SwitchState by remember {
        mutableStateOf(SwitchState.OPEN)
    }
    val accountState: SwitchState by remember {
        mutableStateOf(SwitchState.CLOSE)
    }
    val addState: SwitchState by remember {
        mutableStateOf(SwitchState.CLOSE)
    }

    val targetValue: Dp = when(eyeState) {
        SwitchState.OPEN -> 210.dp
        SwitchState.CLOSE -> 130.dp
    }
    val surfaceHeight by animateDpAsState(targetValue = targetValue)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(surfaceHeight)
            .padding(horizontal = 10.dp)
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
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 40.dp, bottom = 30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = ACN_Balance,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                )
                Spacer(modifier = Modifier.size(15.dp))
                Image(
                    painter = painterResource(
                        when(eyeState) {
                            SwitchState.OPEN -> R.drawable.img_eye_visible_white
                            SwitchState.CLOSE -> R.drawable.img_eye_invisible_white
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            eyeState = when (eyeState) {
                                SwitchState.CLOSE -> SwitchState.OPEN
                                SwitchState.OPEN -> SwitchState.CLOSE
                            }
                        },
                )
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(R.drawable.img_account_white),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
                Spacer(modifier = Modifier.size(15.dp))
                Image(
                    painter = painterResource(R.drawable.img_plus_white),
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            AnimatedVisibility(
                visible = when(eyeState) {
                    SwitchState.OPEN -> true
                    SwitchState.CLOSE -> false
                }
            ) {
                Column {
                    Text(
                        text = "6747105.1",
                        color = Color.White,
                        fontSize = 45.sp,
                        fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                    Text(
                        text = "¥1747",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }

        }
    }
}

@Composable
private fun KYCCard() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
            .padding(10.dp),
        color = Color(0xFFF9E2DE),
        shape = RoundedCornerShape(40.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
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
                    text = "KYC认证奖励",
                    fontSize = 24.sp,
                    color = Color(0xFF8D655E),
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
                text = "完成KYC认证, 即可领取50 ACN",
                color = Color(0xFF8D655E),
                fontSize = 14.sp
            )
        }
    }
}

val initBottomHeight = 610f
var bottomHeight by mutableStateOf(initBottomHeight)


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun BottomSlider() {
//    var bottomHeight by remember {
//        mutableStateOf(initBottomHeight)
//    }

    val nestedScrollConnection = remember(bottomHeight) {
        BottomSliderNestedScrollConnection(bottomHeight)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(with(LocalDensity.current) { bottomHeight.toDp() })
            .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
            .background(Color.White)
            .padding(vertical = 30.dp)
            .nestedScroll(nestedScrollConnection)

    ) {
        StickyListView()
    }
}

//@Composable
//private fun updateBottomSliderHeight(delta: Dp) {
//    bottomHeight -= delta
//}

/**
 *  处理嵌套滑动
 */
private class BottomSliderNestedScrollConnection(val height: Float) : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        return if (source == NestedScrollSource.Drag && available.y < 0) {
            if (height < 1400) {
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
            if (height >= 610) {
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