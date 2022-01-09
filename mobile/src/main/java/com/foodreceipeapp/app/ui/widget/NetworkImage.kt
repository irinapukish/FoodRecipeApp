package com.foodreceipeapp.app.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState

@Composable
fun NetworkImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    fadeIn: Boolean = true,
    contentScale: ContentScale = ContentScale.Crop,
    loadingContent: @Composable () -> Unit
) {
    Box(modifier) {
        val painter = rememberCoilPainter(
            request = url,
            fadeIn = fadeIn
        )
        Image(
            painter = painter,
            contentDescription = contentDescription,
            contentScale = contentScale,
            modifier = Modifier.fillMaxSize()
        )
        if (painter.loadState is ImageLoadState.Loading) {
            loadingContent()
        }
    }
}
