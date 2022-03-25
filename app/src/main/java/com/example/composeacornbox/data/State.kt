package com.example.composeacornbox.data

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 状态
 */

enum class AppState {
    Splash,
    Home
}

enum class SwitchState {
    Open,
    Close
}

fun SwitchState.reverse(): SwitchState {
    val state: SwitchState = when(this) {
        SwitchState.Open -> SwitchState.Close
        SwitchState.Close -> SwitchState.Open
    }
    return state
}


enum class AnimState {
    Start,
    Stop
}

fun AnimState.reverse(): AnimState {
    val state: AnimState = when(this) {
        AnimState.Start -> AnimState.Stop
        AnimState.Stop -> AnimState.Start
    }
    return state
}

enum class HeaderState {
    Eye,
    Account,
    Add
}

enum class AccountState {
    Transfer,
    Collection
}

enum class WalletCreateState {
    Welcome,
    Secure,
    Friend,
    Explore
}