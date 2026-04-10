package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface StackOverflowService {
    @GET("users?page=1&pagesize=20&order=desc&sort=reputation&site=stackoverflow")
    suspend fun getUsers(): GetUsersResponse

     @GET("users/{userId}?order=desc&sort=reputation&site=stackoverflow")
     suspend fun getUserDetail(@Path("userId") userId:String): GetUsersResponse
}
