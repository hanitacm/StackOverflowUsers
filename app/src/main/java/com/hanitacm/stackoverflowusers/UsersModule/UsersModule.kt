package com.hanitacm.stackoverflowusers.UsersModule

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.hanitacm.stackoverflowusers.ui.UserListViewModel

class UsersModule(context: Context) {
    fun inject() = object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            return UserListViewModel() as T
        }
    }
}
