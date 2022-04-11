package com.example.composeacornbox.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeacornbox.flutter.MethodChannel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */
@HiltViewModel
class AcnBoxViewModel @Inject constructor(
    private val methodChannel: MethodChannel
) : ViewModel() {

    private val _channelEvent = MutableSharedFlow<ChannelEvent>()
    val channelEvent: SharedFlow<ChannelEvent> = _channelEvent

    private val _channelState = MutableStateFlow(ChannelState.NavigationState)
    val channelState: StateFlow<ChannelState> = _channelState

    fun dispatchAction(action: ChannelAction) {
        when (action) {
            ChannelAction.ObserverNavigationChannel -> initNavigationChannel()
            ChannelAction.ObserverKeyEventChannel -> initKeyEventChannel()
        }
    }

    private fun initNavigationChannel() {
        methodChannel.initNavigationChannel { route ->
            route?.let {
                viewModelScope.launch {
                    _channelEvent.emit(ChannelEvent.NavigationEvent(it))
                }
            }
        }
    }

    private fun initKeyEventChannel() {
        methodChannel.initKeyEventChannel { event ->
            viewModelScope.launch {
                // TODO
            }
        }
    }

}

/**
 *  UI State(页面状态, 是持续的)
 */
sealed class ChannelState {
    object NavigationState : ChannelState()
}

/**
 *  UI Event(一次性事件)
 */
sealed class ChannelEvent {
    data class NavigationEvent(val route: Any) : ChannelEvent()
    data class KeyEvent(val key: Int) : ChannelEvent()
}

/**
 *  UI发出的指令
 */
sealed class ChannelAction {
    object ObserverNavigationChannel : ChannelAction()
    object ObserverKeyEventChannel : ChannelAction()
}