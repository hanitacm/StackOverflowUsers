package com.hanitacm.stackoverflowusers

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

object ImageLoader {

    suspend fun getImageBitmap(url: String): ImageBitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder().url(url).build()
                val response = client.newCall(request).execute()
                response.body.byteStream().use { inputStream ->
                    inputStream.let {
                        BitmapFactory.decodeStream(it)?.asImageBitmap()
                    }
                }
            } catch (e: Exception) {
                Log.e("ImageLoader", "Error loading image: $url", e)
                null
            }
        }
    }
}