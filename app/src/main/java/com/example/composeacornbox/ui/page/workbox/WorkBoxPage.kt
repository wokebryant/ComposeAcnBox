package com.example.composeacornbox.ui.page.workbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.composeacornbox.ui.page.usercenter.WorkBoxViewModel
import com.example.composeacornbox.ui.theme.WorkBoxBg
import com.example.composeacornbox.ui.widget.DarkStatusBar
import com.example.composeacornbox.utils.RouteUtils.back
import com.google.accompanist.insets.statusBarsPadding


/**
 * @Author: LuoJia
 * @Date:
 * @Description: WorkBox页面
 */

@Composable
fun WorkBoxPage(
    navController: NavHostController,
    viewModel: WorkBoxViewModel = hiltViewModel()
) {
    DarkStatusBar()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(WorkBoxBg)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding()
        ) {
            WBHeader {
                navController.back()
            }
            WBBody(viewModel.viewStates)
        }
    }
}


