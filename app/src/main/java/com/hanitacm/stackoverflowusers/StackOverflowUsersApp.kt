package com.hanitacm.stackoverflowusers

import android.app.Application
import android.content.Context
import com.hanitacm.stackoverflowusers.UsersModule.UsersModule

class StackOverflowUsersApp : Application() {
    private lateinit var usersModule: UsersModule

    companion object {
        fun from(applicationContext: Context): UsersModule {
            return (applicationContext as StackOverflowUsersApp).usersModule
        }
    }

    override fun onCreate() {
        super.onCreate()
        usersModule = UsersModule(applicationContext)

    }
}