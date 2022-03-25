package com.example.composeacornbox.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeacornbox.R

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@Composable
fun ScrollBar(color: Color) {
    Card(
        modifier = Modifier.fillMaxWidth().height(52.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column() {

            }
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(R.drawable.img_champion_cup),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_next_white),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}