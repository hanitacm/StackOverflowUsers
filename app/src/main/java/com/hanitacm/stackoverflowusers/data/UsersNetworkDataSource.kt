package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse

interface UsersNetworkDataSource {
    suspend fun getUsers(): GetUsersResponse

    suspend fun getUserDetail(userId:String): GetUsersResponse

}
