package com.hanitacm.stackoverflowusers.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hanitacm.stackoverflowusers.ui.composables.ImageUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserDetailScreen(viewModel: UserDetailViewModel) {
    val snackbarHostState = remember { SnackbarHostState() }

    val uiState: UserDetailUiState by viewModel.viewState.collectAsStateWithLifecycle()


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
        (uiState as? UserDetailUiState.Success)?.let {
            val user = (uiState as UserDetailUiState.Success).user
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImageUrl(url = user.profileImage)
                Text(text = user.name)
            }
        }


    }
}