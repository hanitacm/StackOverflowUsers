package com.hanitacm.stackoverflowusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.hanitacm.stackoverflowusers.ui.UserListScreen
import com.hanitacm.stackoverflowusers.ui.UserListViewModel
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    private val viewModel: UserListViewModel by viewModels {
        StackOverflowUsersApp.from(applicationContext).inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowUsersTheme {
                UserListScreen(viewModel = viewModel)
            }
        }
    }
}