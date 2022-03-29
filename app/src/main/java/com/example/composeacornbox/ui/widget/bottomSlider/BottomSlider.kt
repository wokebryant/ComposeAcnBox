package com.example.composeacornbox.ui.widget.bottomSlider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.composeacornbox.ui.theme.headerExpandHeight
import com.example.composeacornbox.ui.theme.kycCardHeight
import com.example.composeacornbox.utils.SizeUtils

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 底部拖动Layout
 */

private val defaultHeight: Float
    get() {
        val screenHeight = SizeUtils.getRealScreenSize().y
        val headerHeight = SizeUtils.dp2px(headerExpandHeight.value)
        val kycCardHeight = SizeUtils.dp2px(kycCardHeight.value)
        val height = screenHeight - headerHeight - kycCardHeight
        return height.toFloat()
    }

private val defaultMaxDragOffset: Float
    get() {
        val screenHeight = SizeUtils.getRealScreenSize().y
        val statusBarHeight = SizeUtils.getStatusBarHeight()
        val topPadding = SizeUtils.dp2px(50f)
        return screenHeight - defaultHeight - statusBarHeight - topPadding
    }

@Composable
fun BottomSlider(
    initHeight: Float = defaultHeight,
    maxDragOffset: Float = defaultMaxDragOffset,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val state = rememberBottomSliderState(maxDragOffset, initHeight)

    val nestedScrollConnection = remember(state, coroutineScope) {
        BottomSliderNestedScrollConnection(state, coroutineScope)
    }
    val currentHeight = with(LocalDensity.current) {
        (initHeight + state.dragOffset).toDp()
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(currentHeight)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            .background(Color.White)
            .padding(top = 10.dp)
            .nestedScroll(nestedScrollConnection),
    ) {
        content()
    }
}