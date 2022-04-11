package com.example.composeacornbox.data

import androidx.compose.ui.graphics.Color

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

data class Account(
    val avatarUrl: String,
    val acnCoinNum: Double,
    val rmbNum: Double
)

data class DApp(
    val imgUrl: String,
    val name: String
)

data class Recommend(
    val name: String,
    val cover: String,
    val title: String,
    val bgColor: Color
)

data class UserCenter(
    val name: String,
    val state: String,
    val group: Int
)

data class WorkBox(
    val title: String,
    val content: String,
    val icon: String,
    val group: Int,
    val bgUrl: String = ""
)

data class SalaryRecord(
    val date: String,
    val salary: String
)