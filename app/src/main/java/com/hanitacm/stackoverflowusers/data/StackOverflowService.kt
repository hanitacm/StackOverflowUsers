package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse
import retrofit2.http.GET
 interface StackOverflowService {
    @GET("users?page=1&pagesize=20&order=desc&sort=reputation&site=stackoverflow")
    suspend fun getUsers(): GetUsersResponse
}
