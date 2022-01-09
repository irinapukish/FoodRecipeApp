package com.foodreceipeapp.app.ui.launcher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.foodreceipeapp.app.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LauncherView(
    modifier: Modifier = Modifier,
    onLauncherComplete: (LaunchDestination) -> Unit
) {
    val viewModel: LauncherViewModel = viewModel()
    val viewState by viewModel.state.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onLauncherComplete)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout(viewState.launchDestination)
        }

        Image(
            painter = painterResource(id = R.drawable.ic_delish_logo),
            contentDescription = null
        )
    }
}
