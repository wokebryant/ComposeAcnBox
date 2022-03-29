package com.example.composeacornbox.ui.page.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeacornbox.data.*
import com.example.composeacornbox.ui.page.usercenter.UserCenterPage
import com.example.composeacornbox.ui.widget.AccountDialog
import com.example.composeacornbox.ui.widget.HomeStickyListView
import com.example.composeacornbox.ui.widget.KYCBottomSheetLayout
import com.example.composeacornbox.ui.widget.LightStatusBar
import com.example.composeacornbox.ui.widget.bottomSlider.BottomSlider

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 首页
 */

/** State变量,用于控制重组 */
private var avatarState by mutableStateOf(SwitchState.Close)
private var addState by mutableStateOf(SwitchState.Close)
private var kycBottomLayoutState by mutableStateOf(SwitchState.Close)
private var kycCardAnimState by mutableStateOf(AnimState.Stop)

@ExperimentalFoundationApi
@Composable
@ExperimentalMaterialApi
fun HomePage(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val viewState = viewModel.viewStates
    LightStatusBar()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            HeaderWithState(navController, viewState)
            KycCardWithState()
        }
        BottomSliderWithState(navController, viewState)
        AccountDialogWithState()
        UserCenterPageWithState(viewState)
        KYCBottomSheetLayoutWithState()

        LaunchedEffect(Unit) {
            kycCardAnimState = AnimState.Start
        }
    }
}

/**
 *  头部区域
 */
@Composable
private fun HeaderWithState(
    navController: NavHostController,
    viewState: HomeViewState
) {
    Header(navController, avatarState, viewState) { headerState, switchState ->
        when (headerState) {
            HeaderState.Eye -> {

            }
            HeaderState.Account -> {
                avatarState = switchState
            }
            HeaderState.Add -> {
                addState = addState.reverse()
            }
        }
    }
}

/**
 * KYC认证卡片
 */
@Composable
private fun KycCardWithState() {
    KycCard(kycCardAnimState) {
        kycBottomLayoutState = it
    }
}

/**
 *  底部可拖动布局
 */
@ExperimentalFoundationApi
@Composable
private fun BottomSliderWithState(
    navController: NavHostController,
    viewState: HomeViewState
) {
    BottomSlider {
        HomeStickyListView(navController, viewState)
    }
}

/**
 * KYC认证弹窗
 */
@ExperimentalMaterialApi
@Composable
private fun KYCBottomSheetLayoutWithState() {
    KYCBottomSheetLayout(kycBottomLayoutState) { state ->
        kycBottomLayoutState = when(state) {
            ModalBottomSheetValue.Hidden -> SwitchState.Close
            ModalBottomSheetValue.Expanded -> SwitchState.Open
            ModalBottomSheetValue.HalfExpanded -> SwitchState.Open
        }
    }
}

/**
 *  用户中心页面
 */
@Composable
private fun UserCenterPageWithState(viewState: HomeViewState) {
    UserCenterPage(avatarState, viewState.userCenterState) {
        avatarState = SwitchState.Close
    }
}

/**
 *  转账收款弹窗
 */
@Composable
private fun AccountDialogWithState() {
    val visible = addState == SwitchState.Open
    AnimatedVisibility(visible) {
        AccountDialog { accountState, close ->
            when(accountState) {
                AccountState.Transfer -> {}
                AccountState.Collection -> {}
                else -> {}
            }
            when {
                close -> addState = SwitchState.Close
            }
        }
    }

}