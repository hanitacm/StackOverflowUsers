package com.hanitacm.stackoverflowusers.data

import android.net.http.HttpException
import com.hanitacm.stackoverflowusers.ui.FakeFolloweeDao
import com.hanitacm.stackoverflowusers.ui.model.domain.User

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class UsersRepositoryTest {
    @Test
    fun `users should return a list of users from the data source`() =
        runTest {
            val usersDataSource = UsersDataSourceSuccessfulResponse()
            val usersRepository = UsersRepositoryImpl(usersDataSource, FakeFolloweeDao())
            val expectedUsers = listOf(
                User(
                    id = 0,
                    name = "User Name 0",
                    reputation = 1000,
                    profileImage = "http://example.com/image/0.jpg"
                ),
                User(
                    id = 1,
                    name = "User Name 1",
                    reputation = 1100,
                    profileImage = "http://example.com/image/1.jpg"
                ), User(
                    id = 2,
                    name = "User Name 2",
                    reputation = 1200,
                    profileImage = "http://example.com/image/2.jpg"
                )
            )

            assertEquals(expectedUsers, usersRepository.users.first())
        }

    @Test
    fun `when users throws an exception usersRepository should throw it`() {
        val usersDataSource = UsersDataSourceErrorResponse()
        val usersRepository = UsersRepositoryImpl(usersDataSource, FakeFolloweeDao())

        assertThrows(HttpException::class.java) {
            runTest { usersRepository.users.first() }
        }
    }

    @Test
    fun `followees should return userId list of followee users`() = runTest {
        val repository = UsersRepositoryImpl(UsersDataSourceSuccessfulResponse(), FakeFolloweeDao())
        repository.followUser(123)
        repository.followUser(321)

        val expectedFollowees = listOf(123, 321)
        val followees = repository.followees.first()

        assertEquals(expectedFollowees, followees)
    }

    @Test
    fun `followUser should add userId to followees`() = runTest {
        val repository = UsersRepositoryImpl(UsersDataSourceSuccessfulResponse(), FakeFolloweeDao())

        repository.followUser(123)

        val followees = repository.followees.first()
        assertEquals(listOf(123), followees)
    }

    @Test
    fun `unfollowUser should remove previously followed user`() = runTest {
        val repository = UsersRepositoryImpl(UsersDataSourceSuccessfulResponse(), FakeFolloweeDao())
        repository.followUser(123)

        repository.unfollowUser(123)

        val followees = repository.followees.first()
        assertTrue(followees.isEmpty())
    }
}