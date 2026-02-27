package com.hanitacm.stackoverflowusers.ui.model

import com.hanitacm.stackoverflowusers.ui.model.domain.User
import com.hanitacm.stackoverflowusers.ui.model.ui.UserUi
import java.util.Locale

internal fun User.asUiModel(isFollowee: Boolean) = UserUi(
    id = id,
    name = name,
    profileImage = profileImage,
    reputation = String.format(Locale.getDefault(), "%,d", reputation),
    isFollowee = isFollowee,
)
