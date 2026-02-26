package com.hanitacm.stackoverflowusers.data

import android.net.http.HttpException
import com.hanitacm.stackoverflowusers.data.model.dto.BadgeCounts
import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse
import com.hanitacm.stackoverflowusers.data.model.dto.UserResponse

internal class UsersDataSourceSuccessfulResponse : UsersNetworkDataSource {
    override suspend fun getUsers(): GetUsersResponse {
        return GetUsersResponse(
            items = List(3) { index ->
                UserResponse(
                    badgeCounts = BadgeCounts(bronze = 1, silver = 2, gold = 3),
                    accountId = index,
                    isEmployee = false,
                    lastModifiedDate = 1700000000L,
                    lastAccessDate = 1700000000L,
                    reputationChangeYear = 100,
                    reputationChangeQuarter = 50,
                    reputationChangeMonth = 20,
                    reputationChangeWeek = 10,
                    reputationChangeDay = 5,
                    reputation = 1000 + index * 100,
                    creationDate = 1600000000L,
                    userType = "registered",
                    userId = index,
                    location = "Location $index",
                    websiteUrl = "http://example.com/$index",
                    link = "http://stackoverflow.com/users/$index",
                    profileImage = "http://example.com/image/$index.jpg",
                    displayName = "User Name $index"
                )
            },
            hasMore = false,
            quotaMax = 10000,
            quotaRemaining = 9999
        )
    }

}

internal class UsersDataSourceErrorResponse : UsersNetworkDataSource {
    override suspend fun getUsers(): GetUsersResponse = throw HttpException("network error", null)

}
