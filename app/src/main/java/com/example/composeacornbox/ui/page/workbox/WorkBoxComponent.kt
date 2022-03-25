package com.example.composeacornbox.ui.page.workbox

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.WorkBox
import com.example.composeacornbox.ui.page.usercenter.WorkBoxState
import com.example.composeacornbox.ui.theme.WorkBoxGray
import com.example.composeacornbox.ui.widget.WorkBoxItem

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@Composable
fun WBHeader(onlyClose: Boolean = false, onClose: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_workbox_power),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(24.dp).clickable {
                    onClose.invoke()
                }
            )
            if (!onlyClose) {
                Spacer(modifier = Modifier.size(30.dp))
                Text(
                    text = StringConstant.workBox,
                    fontWeight = FontWeight.W400,
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                TextButton(
                    modifier = Modifier.size(28.dp),
                    shape = CircleShape,
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Text(
                        text = StringConstant.w,
                        fontSize = 14.sp,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(WorkBoxGray))
    }

}

@Composable
fun WBBody(
    viewState: WorkBoxState
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val workBoxList = viewState.workBoxList
        val bigCardList = ArrayList<WorkBox>()
        var currentGroup = -1
        for (workBox in workBoxList) {
            val group = workBox.group
            if (group != currentGroup) {
                when (group) {
                    0 -> {
                        item {
                            WorkBoxSmallCard(workBox)
                        }
                    }

                    1 -> {
                        bigCardList.add(workBox)
                    }

                    2 -> {
                        item {
                            WorkBoxBigCard(bigCardList)
                        }
                        item {
                            WorkBoxSmallCard(workBox)
                        }
                    }

                    3 -> {
                        item {
                            WorkBoxSmallCard(workBox)
                        }
                    }
                }
            } else {
                bigCardList.add(workBox)
            }
            currentGroup = workBox.group
        }

    }
}



@Composable
fun WorkBoxSmallCard(workBox: WorkBox) {
    Card(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        WorkBoxItem(workBox)
    }
}

@Composable
fun WorkBoxBigCard(workBoxList: List<WorkBox>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (workbox in workBoxList) {
                WorkBoxItem(workbox)
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}