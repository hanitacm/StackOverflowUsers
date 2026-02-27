package com.hanitacm.stackoverflowusers.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FolloweeDao {
    @Query("SELECT * FROM followee")
    fun getFollowees(): Flow<List<Followee>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFollowee(item: Followee)

    @Query("DELETE FROM followee WHERE userId = :userId")
    fun deleteFolloweeById(userId: Int)
}