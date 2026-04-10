package com.hanitacm.stackoverflowusers

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.util.LruCache
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
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
    private val deferredRequest = mutableMapOf<String, Deferred<Bitmap?>>()
    private val mutex = Mutex()

    suspend fun getImageBitmap(url: String): Bitmap? = coroutineScope {
        cache[url]?.let { return@coroutineScope it }

        val deferred = mutex.withLock {
            cache[url]?.let { return@withLock CompletableDeferred(it) }

            deferredRequest.getOrPut(url) {
                async(Dispatchers.IO) {
                    try {
                        downloadBitmap(url)
                    } finally {
                        mutex.withLock { deferredRequest.remove(url) }
                    }

                }
            }
        }
        deferred.await()
    }

    private fun downloadBitmap(url: String): Bitmap? {
        return try {
            val request = Request.Builder().url(url).build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return null

                response.body.byteStream().use { inputStream ->
                    BitmapFactory.decodeStream(inputStream)?.also { bitmap ->
                        cache.put(url, bitmap)
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("ImageLoader", "Error loading image: $url", e)
            null
        }

    }
}