package com.hanitacm.stackoverflowusers

import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

object ImageLoader {
    private val cache = LruCache<String, ImageBitmap>((Runtime.getRuntime().maxMemory() / 1024 / 1024).toInt()/ 8)
    suspend fun getImageBitmap(url: String): ImageBitmap? {
        return cache[url]
            ?: withContext(Dispatchers.IO) {
                try {
                    val client = OkHttpClient()
                    val request = Request.Builder().url(url).build()
                    val response = client.newCall(request).execute()
                    response.body.byteStream().use { inputStream ->
                        inputStream.let {
                            val bitmap = BitmapFactory.decodeStream(it)?.asImageBitmap()
                            cache.put(url, bitmap)
                            return@withContext bitmap
                        }
                    }
                } catch (e: Exception) {
                    Log.e("ImageLoader", "Error loading image: $url", e)
                    null
                }
            }
    }
}