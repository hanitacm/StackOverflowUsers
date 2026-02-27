package com.hanitacm.stackoverflowusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.asUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UsersRepository) : ViewModel() {
    private val _viewState =
        MutableStateFlow<UserListUiState>(UserListUiState.Loading)
    val viewState: StateFlow<UserListUiState>
        get() = _viewState

    init {
        viewModelScope.launch {
            runCatching { repository.getUserList() }
                .onSuccess { _viewState.value = UserListUiState.Success(it.asUiModel()) }
                .onFailure { _viewState.value = UserListUiState.Error }
        }
    }

    fun followUser() {
        TODO("Not yet implemented")
    }

    fun unFollowUser() {
        TODO("Not yet implemented")
    }
}





