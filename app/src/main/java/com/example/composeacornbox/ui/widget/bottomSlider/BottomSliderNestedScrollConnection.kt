package com.example.composeacornbox.ui.widget.bottomSlider

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.unit.Velocity
import com.example.composeacornbox.constant.StringConstant.LOG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 处理BottomSlider滑动事件
 */
internal class BottomSliderNestedScrollConnection(
    private val state: BottomSliderState,
    private val coroutineScope: CoroutineScope,
): NestedScrollConnection {

    override fun onPreScroll(
        available: Offset,
        source: NestedScrollSource
    ): Offset = when {
        state.dragState == DragState.DragTop -> Offset.Zero

        source == NestedScrollSource.Drag && available.y < 0 -> onScroll(available)

        else -> Offset.Zero
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset = when {
        state.dragState == DragState.DragBottom -> Offset.Zero

        source == NestedScrollSource.Drag && available.y > 0 -> onScroll(available)

        else -> Offset.Zero
    }

    private fun onScroll(available: Offset): Offset {
        state.dragState = DragState.Drag
        coroutineScope.launch {
            Log.i(LOG,  " available: ${available.y}")
            val currentOffset = state.dragOffset - available.y
            val limitOffset = currentOffset.coerceAtLeast(0f).coerceAtMost(state.maxDragOffset)
            when {
                limitOffset >= state.maxDragOffset -> state.dragState = DragState.DragTop
                limitOffset <= 0f -> state.dragState = DragState.DragBottom
            }
            state.snapOffsetTo(limitOffset)
        }
        return Offset(x = 0f, y = available.y)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        return super.onPreFling(available)
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        return super.onPostFling(consumed, available)
    }

}