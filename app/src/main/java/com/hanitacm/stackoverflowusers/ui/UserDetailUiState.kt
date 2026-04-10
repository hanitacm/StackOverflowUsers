package com.hanitacm.stackoverflowusers.ui

import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi

sealed class UserDetailUiState {
    data class Success(val user: UserUi) : UserDetailUiState()
    data object Loading : UserDetailUiState()
    data class Error(val error: String) : UserDetailUiState()
}





