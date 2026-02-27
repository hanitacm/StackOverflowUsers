package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.model.asDomainModel
import com.hanitacm.stackoverflowusers.ui.model.domain.User

interface UsersRepository {
    suspend fun getUserList(): List<User>
}

class UsersRepositoryImpl(private val usersNetworkDataSource: UsersNetworkDataSource) :
    UsersRepository {
    override suspend fun getUserList(): List<User> {
        return usersNetworkDataSource.getUsers().asDomainModel()
    }
}
