package com.hanitacm.stackoverflowusers.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.hanitacm.stackoverflowusers.ui.model.User

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UserListScreen(viewModel: UserListViewModel, modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = "Top Stack Overflow Users") },
            )
        },
    ) { paddingValues ->
        UserList(
            users = listOf<User>(
                User("Hanita", 10),
                User("Hanita2", 10),
                User("Hanita3", 10),
                User("Hanita4", 10),
            ),
            modifier = Modifier.padding(paddingValues),
        )
    }
}