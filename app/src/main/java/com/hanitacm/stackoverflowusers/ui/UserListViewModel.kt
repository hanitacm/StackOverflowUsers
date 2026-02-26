package com.hanitacm.stackoverflowusers.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.stackoverflowusers.data.UsersRepository
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UsersRepository) : ViewModel() {
    init {
        viewModelScope.launch {
            try {
                val u = repository.getUserList()

            } catch (e: Exception) {
                Log.e("USERS", e.message.orEmpty())
            }
        }
    }
}



