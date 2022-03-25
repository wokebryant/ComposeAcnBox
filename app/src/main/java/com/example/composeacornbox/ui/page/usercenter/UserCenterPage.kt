package com.example.composeacornbox.ui.page.usercenter

import android.widget.Toast
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.composeacornbox.R
import com.example.composeacornbox.base.App
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.SwitchState
import com.example.composeacornbox.data.UserCenter
import com.example.composeacornbox.ui.widget.UserCenterItem
import com.example.composeacornbox.utils.SizeUtils
import com.google.accompanist.insets.statusBarsPadding


/**
 * @Author: LuoJia
 * @Date:
 * @Description: 用户中心页面
 */

@Composable
fun UserCenterPage(
    state: SwitchState,
    userCenterList: List<UserCenter>,
    onDismiss: () -> Unit,
) {
    val screenHeight = SizeUtils.getRealScreenSize().y
    val targetValue = when(state) {
        SwitchState.Open -> 0
        SwitchState.Close -> screenHeight
    }
    val offset by animateIntAsState(
        targetValue = targetValue,
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .offset { IntOffset(x = 0, y = offset) }
    ) {
        Column(
          modifier = Modifier.fillMaxSize()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth().height(44.dp),
                shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                color = Color.White
            ) {
                Row {
                    Image(
                        modifier = Modifier
                            .padding(start = 20.dp, top = 10.dp).size(24.dp)
                            .clickable { onDismiss() },
                        painter = painterResource(R.drawable.ic_close_black),
                        contentDescription = null,
                    )
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth().background(Color.White)
            ) {
                item {
                    HeaderPart()
                }
                item {
                    BodyPart(userCenterList)
                }
            }
        }
    }
}

@Composable
fun HeaderPart() {
    Box(
        modifier = Modifier.fillMaxWidth().height(120.dp)
    ) {
        Text(
            text = StringConstant.userCenter,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 20.dp, top = 25.dp)
        )
    }
}

@Composable
fun BodyPart(userCenterList: List<UserCenter>) {
    var currentGroup = userCenterList[0].group
    for (userCenter in userCenterList) {
        UserCenterItem(userCenter) {
            Toast.makeText(App.CONTEXT, " 用户中心Item点击", Toast.LENGTH_SHORT).show()
        }
        if (userCenter.group != currentGroup) {
            Spacer(modifier = Modifier.size(50.dp))
            currentGroup = userCenter.group
        }
    }
}
