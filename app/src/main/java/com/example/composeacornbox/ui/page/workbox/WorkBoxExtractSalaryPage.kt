package com.example.composeacornbox.ui.page.workbox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.ui.theme.*
import com.example.composeacornbox.ui.widget.DarkStatusBar
import com.example.composeacornbox.ui.widget.ExtratSalaryItem

/**
 * @Author: LuoJia
 * @Date:
 * @Description: 提取工资页面
 */

@Composable
fun WorkBoxExtractSalaryPage(navController: NavHostController) {
    DarkStatusBar()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize().statusBarsPadding()
        ) {
            WBHeader(
                title = StringConstant.extractSalaryRecord,
                onClose = {

                }
            )
            SalaryTotalView()
            SalaryRecordListView()
        }
    }
}

@Composable
fun SalaryTotalView() {
    Column(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "61.05599 ACN",
            fontSize = 30.sp,
            fontWeight = FontWeight.W400,
            color = WorkBoxGreen
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = "累计提取",
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            color = WorkBoxHint
        )
    }
}

@Composable
fun SalaryRecordListView() {
    val list = getSalaryRecordList()
    LazyColumn {
        items(list) {
            ExtratSalaryItem(it)
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(WorkBoxBg),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "没有更多了",
                    color = WorkBoxHint,
                    fontSize = 14.sp
                )
            }
        }
    }
}

fun getSalaryRecordList() = listOf(
    SalaryRecord(date = "2021-11-08 10:59", salary = "6.9ACN"),
    SalaryRecord(date = "2021-07-30 1:55", salary = "54.2736ACN")
)

data class SalaryRecord(
  val date: String,
  val salary: String
)