package com.hanitacm.stackoverflowusers.ui

import android.net.http.HttpException
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.domain.User

internal class UsersRepositoryFake() : UsersRepository {
    override suspend fun getUserList() = listOf(
        User(
            id = 0,
            name = "User Name 0",
            reputation = 1000,
            profileImage = "http://example.com/image/0.jpg",

            ),
        User(
            id = 1,
            name = "User Name 1",
            reputation = 1100,
            profileImage = "http://example.com/image/1.jpg",

            ), User(
            id = 2,
            name = "User Name 2",
            reputation = 1200,
            profileImage = "http://example.com/image/2.jpg",
        )
    )
}

internal class UsersRepositoryErrorResponse : UsersRepository {
    override suspend fun getUserList(): List<User> {
        throw HttpException("network error", null)
    }
}
