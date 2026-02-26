package com.hanitacm.stackoverflowusers.ui

import com.hanitacm.stackoverflowusers.ui.model.User

sealed class UserListUiState {
    data object Loading : UserListUiState()
    data class Success(val users: List<User>) : UserListUiState()
    data class Error(val message: String) : UserListUiState()
}
