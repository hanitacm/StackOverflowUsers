package com.hanitacm.stackoverflowusers.ui

import android.net.http.HttpException
import com.hanitacm.stackoverflowusers.data.UsersRepository
import com.hanitacm.stackoverflowusers.ui.model.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class UsersRepositoryFake() : UsersRepository {
    override val users =
        flow {
            emit(
                listOf(
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
            )
        }

    override fun getUserDetail(userId: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override val followees = flow { emit(listOf(0, 1)) }

    override suspend fun followUser(userId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun unfollowUser(userId: Int) {
        TODO("Not yet implemented")
    }
}

internal class UsersRepositoryErrorResponse : UsersRepository {
    override val users: Flow<List<User>> = flow { throw HttpException("network error", null) }
    override fun getUserDetail(userId: Int): Flow<User> {
        TODO("Not yet implemented")
    }

    override val followees = flow { emit(listOf(0, 1)) }

    override suspend fun followUser(userId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun unfollowUser(userId: Int) {
        TODO("Not yet implemented")
    }
}
