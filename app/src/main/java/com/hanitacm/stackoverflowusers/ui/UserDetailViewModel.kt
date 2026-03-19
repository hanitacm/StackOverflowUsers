package com.hanitacm.stackoverflowusers.ui;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.asUiModel
import com.hanitacm.stackoverflowusers.ui.model.domain.User
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class UserDetailViewModel(userId: Int, usersRepository: UsersRepository) : ViewModel() {
    val viewState: StateFlow<UserDetailUiState> =  usersRepository.getUserDetail(userId)
        .map<User, UserDetailUiState> { user -> UserDetailUiState.Success(user.asUiModel(false)) }
        .catch { error -> emit(UserDetailUiState.Error(error.message.orEmpty())) }
        .stateIn(scope=viewModelScope,initialValue = UserDetailUiState.Loading,started = SharingStarted.WhileSubscribed(5000))

}
