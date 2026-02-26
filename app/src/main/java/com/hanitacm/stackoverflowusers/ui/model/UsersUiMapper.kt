package com.hanitacm.stackoverflowusers.ui.model

import com.hanitacm.stackoverflowusers.ui.model.domain.User
import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi
import java.util.Locale

internal fun List<User>.asUiModel(): List<UserUi> {
    return map {
        UserUi(
            id = it.id,
            name = it.name,
            profileImage = it.profileImage,
            reputation = String.format(Locale.getDefault(), "%,d", it.reputation)
        )
    }
}
