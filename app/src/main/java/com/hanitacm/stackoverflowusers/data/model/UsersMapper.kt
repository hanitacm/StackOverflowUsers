package com.hanitacm.stackoverflowusers.data.model

import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse
import com.hanitacm.stackoverflowusers.data.model.dto.UserResponse
import com.hanitacm.stackoverflowusers.ui.model.domain.User

internal fun GetUsersResponse.asDomainModel(): List<User> =
    this.items.map { dto -> dto.asDomainModel() }


internal fun UserResponse.asDomainModel(): User = User(
    id = this.userId,
    name = this.displayName,
    reputation = this.reputation,
    profileImage = this.profileImage
)