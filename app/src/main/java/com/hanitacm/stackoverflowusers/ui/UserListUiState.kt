package com.hanitacm.stackoverflowusers.ui

import com.hanitacm.stackoverflowusers.ui.model.domain.User
import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi

sealed class UserListUiState {
    data object Loading : UserListUiState()
    data class Success(val users: List<UserUi>) : UserListUiState()
    data class Error(val message: String) : UserListUiState()
}
