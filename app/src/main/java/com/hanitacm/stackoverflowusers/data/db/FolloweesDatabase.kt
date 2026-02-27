package com.hanitacm.stackoverflowusers.data.db

import android.content.Context
import androidx.room.Database

import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Followee::class], version = 1, exportSchema = false)
abstract class FolloweesDatabase : RoomDatabase() {
    companion object {
        fun getDb(context: Context) =
            Room.databaseBuilder(
                context,
                FolloweesDatabase::class.java,
                "followees",
            ).build()
    }

    abstract val followeeDao: FolloweeDao
}
