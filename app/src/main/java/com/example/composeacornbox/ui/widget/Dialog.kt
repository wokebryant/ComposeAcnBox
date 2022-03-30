package com.example.composeacornbox.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.AccountState
import com.google.accompanist.insets.statusBarsPadding

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@Composable
fun AccountDialog(onClick: (AccountState?, Boolean) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .clickable (
                onClick = { onClick(null, true) },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ),
            contentAlignment = Alignment.TopEnd
    ) {
        Card(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 40.dp, end = 10.dp).width(130.dp),
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.White,
            elevation = 5.dp
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable { onClick(AccountState.Transfer, true) }
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_scan_black),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = StringConstant.transfer,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth().height(60.dp).clickable {
                        onClick(AccountState.Collection, true)
                    }
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_receive_black),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = StringConstant.collection,
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W700
                    )
                }
            }
        }
    }

}