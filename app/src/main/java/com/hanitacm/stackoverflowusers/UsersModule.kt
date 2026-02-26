package com.hanitacm.stackoverflowusers

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.hanitacm.stackoverflowusers.data.StackOverflowService
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.UserListViewModel
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class UsersModule(context: Context) {
    fun inject() = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            return UserListViewModel(usersRepository) as T
        }
    }

    private val usersRepository by lazy {
        UsersRepository(
            stackOverflowService,
        )
    }

    private val stackOverflowService: StackOverflowService by lazy {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true // Optional: good for default values on nulls
        }

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(StackOverflowService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://api.stackexchange.com/2.2/"
    }
}