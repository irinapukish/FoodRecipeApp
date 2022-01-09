package com.foodreceipeapp.app.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun NotificationBanner(
    isOpen: Boolean,
    notificationMessage: String,
    backgroundColor: Color,
    textColor: Color,
    buttonText: String,
    buttonTextColor: Color,
    onRetryClicked: () -> Unit
) {
    AnimatedVisibility(visible = isOpen) {
        Row(
            Modifier.background(backgroundColor)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp).weight(3f),
                text = notificationMessage,
                color = textColor,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 2
            )

            TextButton(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp).weight(1f),
                onClick = { onRetryClicked() }
            ) {
                Text(
                    text = buttonText,
                    color = buttonTextColor,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}
