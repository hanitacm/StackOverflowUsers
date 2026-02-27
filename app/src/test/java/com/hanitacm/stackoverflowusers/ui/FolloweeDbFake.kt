package com.hanitacm.stackoverflowusers.ui

import com.hanitacm.stackoverflowusers.data.db.Followee
import com.hanitacm.stackoverflowusers.data.db.FolloweeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FakeFolloweeDao : FolloweeDao {

    private val data = mutableListOf<Followee>()

    override fun getFollowees(): Flow<List<Followee>> = flow{
        emit(data)
    }

    override fun insertFollowee(item: Followee) {
        data.add(item)
    }

    override fun deleteFolloweeById(userId: Int) {
        data.removeAll { it.userId == userId }
    }
}