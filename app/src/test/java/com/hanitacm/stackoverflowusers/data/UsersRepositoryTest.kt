package com.hanitacm.stackoverflowusers.data

import android.net.http.HttpException
import com.hanitacm.stackoverflowusers.ui.model.domain.User
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertThrows
import org.junit.Test

class UsersRepositoryTest {
    @Test
    fun `when getUserList is called, it should return a list of users from the data source`() =
        runTest {
            val usersDataSource = UsersDataSourceSuccessfulResponse()
            val usersRepository = UsersRepositoryImpl(usersDataSource)
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

            val result = usersRepository.getUserList()


            assertEquals(expectedUsers, result)

        }

    @Test
    fun `when data source throws an exception usersRepository should throw it`() {
        val usersDataSource = UsersDataSourceErrorResponse()
        val usersRepository = UsersRepositoryImpl(usersDataSource)

        assertThrows(HttpException::class.java) {
            runTest { usersRepository.getUserList() }
        }
    }
}


