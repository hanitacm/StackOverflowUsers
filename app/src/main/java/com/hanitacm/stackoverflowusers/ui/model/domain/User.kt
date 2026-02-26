package com.hanitacm.stackoverflowusers.ui.model.domain

data class User(
    val id: Int,
    val name: String,
    val reputation: Int,
    val profileImage: String,
)