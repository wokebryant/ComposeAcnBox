package com.example.composeacornbox.ui.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.composeacornbox.constant.RouteName
import com.example.composeacornbox.constant.StringConstant
import com.example.composeacornbox.data.DApp
import com.example.composeacornbox.data.Recommend
import com.example.composeacornbox.ui.page.home.HomeViewState
import com.example.composeacornbox.utils.RouteUtils

@ExperimentalFoundationApi
@Composable
fun HomeStickyListView(
    navController: NavHostController,
    viewState: HomeViewState
) {
    val sections = StringConstant.BottomSliderSections
    LazyColumn(
    ) {
        sections.forEachIndexed { index, section ->
            stickyHeader {
                Text(
                    text = section,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(10.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.W700,
                    fontSize = 18.sp
                )
            }

            when(index) {
                0 -> item {
                    DAppListView(navController, viewState.dAppsState)
                    Spacer(modifier = Modifier.size(20.dp))
                }
                1 -> item {
                    RecommendListView(navController, viewState.recommendState)
                    Spacer(modifier = Modifier.size(10.dp))
                }
            }
        }
    }
}

@Composable
fun DAppListView(
    navController: NavHostController,
    dApps: List<DApp>,
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(dApps) { dApp ->
            DAppItem(dApp) { itemName ->
                when(itemName) {
                    "WorkBox" -> {
                        RouteUtils.navTo(navController, RouteName.WORK_BOX_WELCOME)
                    }
                }
            }
        }
    }
}

@Composable
fun RecommendListView(
    navController: NavHostController,
    recommends: List<Recommend>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        for (recommend in recommends) {
            RecommendItem(recommend) { itemName ->
                when(itemName) {
                    "WorkBox" -> {
                        RouteUtils.navTo(navController, RouteName.RECOMMEND)
                    }

                    "tata" -> {
                        RouteUtils.navTo(navController, RouteName.RECOMMEND)
                    }
                }
            }
        }
    }
}