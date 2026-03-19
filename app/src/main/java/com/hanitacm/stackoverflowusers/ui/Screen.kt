package com.hanitacm.stackoverflowusers.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {
    @Parcelize
    data object UserList : Screen()

    @Parcelize
    data class UserDetails(val userId: Int) : Screen()
}
