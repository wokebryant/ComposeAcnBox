package com.example.composeacornbox.ui.widget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeacornbox.ui.theme.WorkBoxLightOrange
import com.example.composeacornbox.ui.theme.buttonCorner
import com.example.composeacornbox.ui.theme.buttonHeight

/**
 * @Author: LuoJia
 * @Date:
 * @Description:
 */

@Composable
fun DarkButton(
    content: String,
    onClick: () -> Unit
) {
    PrimaryButton(
        backgroundColor = Color.DarkGray,
        contentColor = Color.Green,
        content = content,
        onClick = onClick
    )
}

@Composable
fun TransButton(
    content: String,
    contentColor: Color = Color.White,
    onClick: () -> Unit
) {
    PrimaryButton(
        backgroundColor = Color.Transparent,
        contentColor = contentColor,
        content = content,
        onClick = onClick,
        fontWeight = FontWeight.W500
    )
}

@Composable
fun OrangeButton(
    content: String,
    contentColor: Color = Color.White,
    onClick: () -> Unit
) {
    PrimaryButton(
        backgroundColor = WorkBoxLightOrange,
        contentColor = contentColor,
        content = content,
        onClick = onClick,
        fontWeight = FontWeight.W500
    )
}

@Composable
fun PrimaryButton(
    backgroundColor: Color,
    contentColor: Color,
    content: String,
    fontSize: Int = 18,
    fontWeight: FontWeight = FontWeight.W700,
    onClick: () -> Unit
) {
    TextButton(
        modifier = Modifier.fillMaxWidth().height(buttonHeight),
        shape = RoundedCornerShape(buttonCorner),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        onClick = onClick
    ) {
        Text(
            text = content,
            fontSize = fontSize.sp,
            fontWeight = fontWeight
        )
    }

}

