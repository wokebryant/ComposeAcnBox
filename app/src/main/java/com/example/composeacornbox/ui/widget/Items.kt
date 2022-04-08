package com.example.composeacornbox.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.composeacornbox.R
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.DApp
import com.example.composeacornbox.data.Recommend
import com.example.composeacornbox.data.UserCenter
import com.example.composeacornbox.data.WorkBox
import com.example.composeacornbox.ui.page.workbox.SalaryRecord
import com.example.composeacornbox.ui.theme.AcnBlack
import com.example.composeacornbox.ui.theme.AcnGreen
import com.example.composeacornbox.ui.theme.WorkBoxGreen
import com.example.composeacornbox.ui.theme.divideLineGray

/**
 * @Author: LuoJia
 * @Date:
 * @Description: Item
 */

@Composable
fun DAppItem(dApp: DApp, onItemClick: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .size(65.dp)
            .border(2.dp, color = divideLineGray, shape = RoundedCornerShape(15.dp))
            .clickable {
                onItemClick(dApp.name)
            },
        shape = RoundedCornerShape(15.dp),
    ) {
        AsyncImage(
            model = dApp.imgUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Composable
fun RecommendItem(recommend: Recommend, onItemClick: (String) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .height(330.dp)
            .clickable { onItemClick(recommend.name) },
        shape = RoundedCornerShape(35.dp),
        color = recommend.bgColor
    ) {
        AsyncImage(
            model = recommend.cover,
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(25.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = recommend.name,
                color = Color.White,
                fontWeight = FontWeight.W300,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = recommend.title,
                color = Color.White,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun UserCenterItem(userCenter: UserCenter, onItemClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().height(60.dp).padding(horizontal = 20.dp)
            .clickable { onItemClick() },
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = userCenter.name,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = userCenter.state,
                color = AcnGreen,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.size(10.dp))
            Image(
                painter = painterResource(R.drawable.ic_next_black),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
            )
        }
        Spacer(modifier = Modifier.size(15.dp))
        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp).background(divideLineGray))
    }
}

@Composable
fun WorkBoxItem(workBox: WorkBox) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = workBox.bgUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = workBox.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    color = Color.Black
                )
                Text(
                    text = workBox.content,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W300,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            AsyncImage(
                model = workBox.icon,
                contentDescription = null,
                modifier = Modifier.size(60.dp)
            )
        }
    }

}

@Composable
fun ExtratSalaryItem(item: SalaryRecord) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.date,
            fontSize = 14.sp,
            color = AcnBlack
        )
        Text(
            modifier = Modifier.weight(1.0f),
            textAlign = TextAlign.End ,
            text = item.salary,
            color = WorkBoxGreen,
            fontSize = 14.sp
        )
    }
}