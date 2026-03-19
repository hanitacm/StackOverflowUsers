package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse

internal class UsersNetworkApi(private val stackOverflowService: StackOverflowService): UsersNetworkDataSource {
    override suspend fun getUsers(): GetUsersResponse {
        return stackOverflowService.getUsers()
    }

    override suspend fun getUserDetail(userId:String): GetUsersResponse {
        return stackOverflowService.getUserDetail(userId)

    }
}