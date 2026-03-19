package com.hanitacm.stackoverflowusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.hanitacm.stackoverflowusers.ui.Screen
import com.hanitacm.stackoverflowusers.ui.UserDetailScreen
import com.hanitacm.stackoverflowusers.ui.UserDetailViewModel
import com.hanitacm.stackoverflowusers.ui.UserListScreen
import com.hanitacm.stackoverflowusers.ui.UserListViewModel
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme

class MainActivity : ComponentActivity() {
    private val viewModel: UserListViewModel by viewModels {
        StackOverflowUsersApp.from(applicationContext).inject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var screen by rememberSaveable { mutableStateOf<Screen>(Screen.UserList) }
            StackOverflowUsersTheme {
                when (val currentScreen = screen) {
                    is Screen.UserDetails -> {
                        val detailViewModel: UserDetailViewModel by viewModels {
                            StackOverflowUsersApp.from(applicationContext)
                                .injectDetail(currentScreen.userId)
                        }
                        UserDetailScreen(viewModel = detailViewModel)
                    }

                    Screen.UserList -> UserListScreen(
                        viewModel = viewModel,
                        onNavigate = { userId: Int -> screen = Screen.UserDetails(userId) })
                }
            }
            BackHandler {
                if (screen is Screen.UserDetails) {
                    screen = Screen.UserList
                }else{
                    finish()
                }
            }
        }
    }
}
