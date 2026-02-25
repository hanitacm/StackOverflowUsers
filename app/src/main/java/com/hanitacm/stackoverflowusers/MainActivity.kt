package com.hanitacm.stackoverflowusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hanitacm.stackoverflowusers.ui.UserListScreen
import com.hanitacm.stackoverflowusers.ui.theme.StackOverflowUsersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StackOverflowUsersTheme {
                UserListScreen()
            }
        }
    }
}