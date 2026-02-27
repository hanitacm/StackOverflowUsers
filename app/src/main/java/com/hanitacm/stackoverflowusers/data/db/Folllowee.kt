package com.hanitacm.stackoverflowusers.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity data class Followee(@PrimaryKey val userId: Int)
