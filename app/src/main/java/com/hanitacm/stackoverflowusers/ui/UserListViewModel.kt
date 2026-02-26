package com.hanitacm.stackoverflowusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.asUiModel
import com.hanitacm.stackoverflowusers.ui.model.domain.User
import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale

class UserListViewModel(private val repository: UsersRepository) : ViewModel() {
    private val _viewState =
        MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val viewState: StateFlow<UserListUiState>
        get() = _viewState

    init {
        viewModelScope.launch {
            runCatching { repository.getUserList() }
                .onSuccess { _viewState.value = UserListUiState.Success(it.asUiModel()) }
                .onFailure { _viewState.value = UserListUiState.Error(it.message ?: "Error") }
        }
    }}





