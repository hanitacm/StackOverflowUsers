package com.hanitacm.stackoverflowusers.ui.model.ui

data class UserUi(
    val id: Int,
    val name: String,
    val reputation: String,
    val profileImage: String,
    val isFollowee: Boolean,
)