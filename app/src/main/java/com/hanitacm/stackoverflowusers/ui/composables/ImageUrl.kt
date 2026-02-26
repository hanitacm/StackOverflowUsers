package com.hanitacm.stackoverflowusers.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import com.hanitacm.stackoverflowusers.ImageLoader

@Composable
internal fun ImageUrl(url: String, modifier: Modifier = Modifier) {
    val imageBitmap by produceState<ImageBitmap?>(initialValue = null, url) {
        value = ImageLoader.getImageBitmap(url)
    }

    Box(modifier = modifier) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = "Image from URL",
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}