package com.hanitacm.stackoverflowusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.asUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UsersRepository) : ViewModel() {
    val viewState: StateFlow<UserListUiState> =
        combine(repository.users, repository.followees) { users, followees ->
            val userUiList = users.map { user ->
                user.asUiModel(isFollowee = followees.contains(user.id))
            }
            UserListUiState.Success(userUiList)
        }.catch<UserListUiState> { emit(UserListUiState.Error) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), UserListUiState.Loading)

    fun followUser(userId: Int) {
        viewModelScope.launch {
            repository.followUser(userId)
        }
    }

    fun unFollowUser(userId: Int) {
        viewModelScope.launch {
            repository.unfollowUser(userId)
        }
    }
}





