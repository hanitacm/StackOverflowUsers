package com.hanitacm.stackoverflowusers.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hanitacm.stackoverflowusers.ui.composables.ErrorMessage
import com.hanitacm.stackoverflowusers.ui.composables.ProgressBar
import com.hanitacm.stackoverflowusers.ui.composables.UserList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserListScreen(viewModel: UserListViewModel) {
    val uiState: UserListUiState by viewModel.viewState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

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
                modifier = Modifier.padding(paddingValues),
            )

            is UserListUiState.Error -> ErrorMessage("Ooops! Something went wrong")


        }
    }
}