package com.example.composeacornbox.ui.widget.bottomSlider

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.MutatorMutex
import androidx.compose.runtime.*

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 滑动State
 */

@Composable
internal fun rememberBottomSliderState(
    maxDragOffset: Float,
    intHeight: Float
) : BottomSliderState {
    return remember {
        BottomSliderState(
            maxDragOffset = maxDragOffset,
            intHeight = intHeight
        )
    }.apply {
        this.maxDragOffset = maxDragOffset
        this.intHeight = intHeight
    }
}

@Stable
class BottomSliderState(
    maxDragOffset: Float,
    intHeight: Float
) {
    private val _dragOffset = Animatable(0f)
    private val mutatorMutex = MutatorMutex()

    var maxDragOffset: Float by mutableStateOf(maxDragOffset)
        internal set

    var intHeight: Float by mutableStateOf(intHeight)
        internal set

    var dragState: DragState by mutableStateOf(DragState.DragBottom)
        internal set

    val dragOffset: Float get() = _dragOffset.value


    internal suspend fun animateOffsetTo(offset: Float) {
        mutatorMutex.mutate {
            _dragOffset.animateTo(offset)
        }
    }

    internal suspend fun snapOffsetTo(offset: Float) {
        mutatorMutex.mutate {
            _dragOffset.snapTo(offset)
        }
    }

}

enum class DragState {
    DragTop,
    Drag,
    DragBottom
}