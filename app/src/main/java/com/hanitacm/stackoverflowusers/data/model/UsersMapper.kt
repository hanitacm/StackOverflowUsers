package com.hanitacm.stackoverflowusers.data.model

import com.hanitacm.stackoverflowusers.data.model.dto.GetUsersResponse
import com.hanitacm.stackoverflowusers.ui.model.User

internal fun GetUsersResponse.asDomainModel(): List<User> = this.items.map { dto ->
    User(
        id = dto.userId,
        name = dto.displayName,
        reputation = dto.reputation,
        profileImage = dto.profileImage
    )
}