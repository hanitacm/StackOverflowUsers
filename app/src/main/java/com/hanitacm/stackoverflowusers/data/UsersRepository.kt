package com.hanitacm.stackoverflowusers.data

import com.hanitacm.stackoverflowusers.data.db.Followee
import com.hanitacm.stackoverflowusers.data.db.FolloweeDao
import com.hanitacm.stackoverflowusers.data.model.asDomainModel
import com.hanitacm.stackoverflowusers.ui.model.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface UsersRepository {
    val users: Flow<List<User>>
    val followees: Flow<List<Int>>
    suspend fun followUser(userId: Int)
    suspend fun unfollowUser(userId: Int)
}

class UsersRepositoryImpl(
    private val usersNetworkDataSource: UsersNetworkDataSource,
    private val followeeDao: FolloweeDao
) : UsersRepository {
    override val users = flow { emit( usersNetworkDataSource.getUsers().asDomainModel() )}
    override val followees = followeeDao.getFollowees().distinctUntilChanged()
        .map { it.map { followee -> followee.userId } }


    override suspend fun followUser(userId: Int) {
        followeeDao.insertFollowee(Followee(userId = userId))
    }

    override suspend fun unfollowUser(userId: Int) {
        followeeDao.deleteFolloweeById(userId = userId)
    }
}
