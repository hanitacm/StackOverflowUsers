package com.hanitacm.stackoverflowusers.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.asUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class UserListViewModel(private val repository: UsersRepository) : ViewModel() {
    private val refreshTrigger = MutableSharedFlow<Unit>()

    val viewState: StateFlow<UserListUiState> = refreshTrigger
        .onStart { emit(Unit) }
        .flatMapLatest {
            combine(repository.users, repository.followees) { users, followees ->
                val userUiList = users.map { user ->
                    user.asUiModel(isFollowee = followees.contains(user.id))
                }
                UserListUiState.Success(userUiList) as UserListUiState
            }
            .onStart { emit(UserListUiState.Loading) }
            .catch { emit(UserListUiState.Error) }
        }
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

    fun onRefresh() {
        viewModelScope.launch {
            refreshTrigger.emit(Unit)
        }
    }
}
