package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.model.asDomainModel
import com.hanitacm.stackoverflowusers.ui.model.User

class UsersRepository(private val usersNetworkDataSource: UsersNetworkDataSource) {
    suspend fun getUserList(): List<User> {
        return usersNetworkDataSource.getUsers().asDomainModel()
    }
}
