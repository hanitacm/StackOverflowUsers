package com.hanitacm.stackoverflowusers.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hanitacm.stackoverflowusers.R
import com.hanitacm.stackoverflowusers.ui.composables.ErrorMessage
import com.hanitacm.stackoverflowusers.ui.composables.ProgressBar
import com.hanitacm.stackoverflowusers.ui.composables.UserList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserListScreen(viewModel: UserListViewModel) {
    val uiState: UserListUiState by viewModel.viewState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Top Stack Overflow Users") },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        when (uiState) {
            is UserListUiState.Loading -> ProgressBar(modifier = Modifier.padding(paddingValues))
            is UserListUiState.Success -> UserList(
                users = (uiState as UserListUiState.Success).users,
                onFollowClick={ userId->
                    viewModel.followUser(userId)
                },
                onUnfollowClick={userId->
                    viewModel.unFollowUser(userId)
                },
                modifier = Modifier.padding(paddingValues),
            )

            is UserListUiState.Error -> ErrorMessage(stringResource(id = R.string.error_message_text))


        }
    }
}