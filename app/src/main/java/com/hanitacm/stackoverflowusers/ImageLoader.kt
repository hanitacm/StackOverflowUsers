package com.hanitacm.stackoverflowusers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

object ImageLoader {
    private val cacheSize = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8
    private val cache = object : LruCache<String, Bitmap>(cacheSize) {
        override fun sizeOf(
            key: String?,
            value: Bitmap
        ): Int {
            return value.byteCount / 1024
        }

    }
    private val client = OkHttpClient()

    suspend fun getImageBitmap(url: String): Bitmap? {
        return cache[url]
            ?: withContext(Dispatchers.IO) {
                try {
                    val request = Request.Builder().url(url).build()

                    client.newCall(request).execute().use { response ->
                        response.body.byteStream().use { inputStream ->
                            inputStream.let {
                                BitmapFactory.decodeStream(it)?.let { bitmap ->
                                    cache.put(url, bitmap)
                                    bitmap
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.e("ImageLoader", "Error loading image: $url", e)
                    null
                }
            }
    }
}