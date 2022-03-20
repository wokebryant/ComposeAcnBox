package com.example.composeacornbox.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.composeacornbox.R

@Composable
fun DAppItem() {
    Surface(
        modifier = Modifier.size(50.dp),
        shape = RoundedCornerShape(15.dp),
        color = Color.Red
    ) {}
}

@Composable
fun RecommendItem() {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(10.dp).height(300.dp),
        shape = RoundedCornerShape(35.dp),
        color = Color.Blue
    ) {

    }
}