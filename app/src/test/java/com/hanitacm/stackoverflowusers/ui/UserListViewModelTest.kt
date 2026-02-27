package com.hanitacm.stackoverflowusers.ui

import com.hanitacm.stackoverflowusers.MainDispatcherRule
import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {
    @get:Rule
    val testCoroutineRule = MainDispatcherRule()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setup() {
        viewModel = UserListViewModel(UsersRepositoryFake())
    }
    @Test
    fun `initially viewModel should emit Loading state`() = runTest {
        assertEquals(UserListUiState.Loading, viewModel.viewState.value)
    }

    @Test
    fun `successful data fetch emits Success state`() = runTest {
        val expectedUsers = listOf(
            UserUi(
                id = 0,
                name = "User Name 0",
                reputation = "1,000",
                profileImage = "http://example.com/image/0.jpg",
                isFollowee = true

                ),
            UserUi(
                id = 1,
                name = "User Name 1",
                reputation = "1,100",
                profileImage = "http://example.com/image/1.jpg",
                isFollowee = true

                ),
            UserUi(
                id = 2,
                name = "User Name 2",
                reputation = "1,200",
                profileImage = "http://example.com/image/2.jpg",
                isFollowee = false
            )
        )

        backgroundScope.launch { viewModel.viewState.collect() }
        advanceUntilIdle()
        assertEquals(UserListUiState.Success(expectedUsers), viewModel.viewState.value)
    }

    @Test
    fun `repository failure emits Error state`() = runTest {
        val viewModel = UserListViewModel(UsersRepositoryErrorResponse())

        backgroundScope.launch { viewModel.viewState.collect() }
        advanceUntilIdle()
        assertEquals(UserListUiState.Error, viewModel.viewState.value)
    }
}