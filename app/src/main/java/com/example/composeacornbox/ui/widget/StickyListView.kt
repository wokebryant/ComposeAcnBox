package com.example.composeacornbox.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.composeacornbox.constant.BottomSliderSections
import com.example.composeacornbox.ui.item.DAppItem
import com.example.composeacornbox.ui.item.RecommendItem

@ExperimentalFoundationApi
@Composable
fun StickyListView() {
    val sections = BottomSliderSections
    LazyColumn(
    ) {
        sections.forEachIndexed { index, section ->
            stickyHeader {
                Text(
                    text = section,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 10.dp, vertical = 15.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.W700
                )
            }

            when(index) {
                0 -> item { DAppListView() }
//
                1 -> item { RecommendListView() }
            }
        }
    }
}

@Composable
fun DAppListView() {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(8) {
            DAppItem()
        }
    }
}

@Composable
fun RecommendListView() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        RecommendItem()
        RecommendItem()
        RecommendItem()
        RecommendItem()
    }
}