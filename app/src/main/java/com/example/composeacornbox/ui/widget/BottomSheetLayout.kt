package com.example.composeacornbox.ui.widget

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.SwitchState
import com.example.composeacornbox.ui.theme.BottomSheetBg
import kotlinx.coroutines.launch


/**
 * @Author: LuoJia
 * @Date:
 * @Description: 底部弹窗布局
 */

@SuppressLint("CoroutineCreationDuringComposition")
@ExperimentalMaterialApi
@Composable
fun KYCBottomSheetLayout(switchState: SwitchState, stateListener: (ModalBottomSheetValue)-> Unit) {
    val initValue = when (switchState) {
        SwitchState.Open -> ModalBottomSheetValue.HalfExpanded
        SwitchState.Close -> ModalBottomSheetValue.Hidden
    }
    // TODO 1.2.0版本的Compose支持跳过显示半屏，等待官方升级至稳定版1.2.0
    val state = rememberModalBottomSheetState(
        initialValue = initValue,
        confirmStateChange = {
            stateListener.invoke(it)
            true
        }
    )

    val scope = rememberCoroutineScope()
    if (switchState == SwitchState.Open) {
        scope.launch { state.show() }
    }
    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = RoundedCornerShape(15.dp),
        sheetBackgroundColor = BottomSheetBg,
        sheetContent = {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_kyc_guide),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .size(width = 180.dp, height = 120.dp)
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    Text(
                        text =StringConstant.kycDialogTitle,
                        color = Color.Black,
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    Text(
                        text = StringConstant.kycDialogContent,
                        color = Color.Black,
                        fontWeight = FontWeight.W300,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    DarkButton(
                        content = StringConstant.start,
                        onClick = {

                        }
                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    TransButton(
                        content = StringConstant.giveUp,
                        contentColor = Color.Black,
                        onClick = {

                        }
                    )
                }
            }
        }
    ) {
        //返回键监听
        BackHandler (
            enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded
                    || state.currentValue == ModalBottomSheetValue.Expanded),
            onBack = {
                scope.launch {
                    state.hide()
                }
                stateListener(ModalBottomSheetValue.Hidden)
            }
        )
    }


}