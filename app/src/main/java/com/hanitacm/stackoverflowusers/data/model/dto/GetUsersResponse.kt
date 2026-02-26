package com.hanitacm.stackoverflowusers.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetUsersResponse(
    @SerialName("items")
    val items: List<UserResponse>,
    @SerialName("has_more")
    val hasMore: Boolean,
    @SerialName("quota_max")
    val quotaMax: Int,
    @SerialName("quota_remaining")
    val quotaRemaining: Int
)

@Serializable
data class UserResponse(
    @SerialName("badge_counts")
    val badgeCounts: BadgeCounts,
    @SerialName("account_id")
    val accountId: Int,
    @SerialName("is_employee")
    val isEmployee: Boolean,
    @SerialName("last_modified_date")
    val lastModifiedDate: Long,
    @SerialName("last_access_date")
    val lastAccessDate: Long,
    @SerialName("reputation_change_year")
    val reputationChangeYear: Int,
    @SerialName("reputation_change_quarter")
    val reputationChangeQuarter: Int,
    @SerialName("reputation_change_month")
    val reputationChangeMonth: Int,
    @SerialName("reputation_change_week")
    val reputationChangeWeek: Int,
    @SerialName("reputation_change_day")
    val reputationChangeDay: Int,
    @SerialName("reputation")
    val reputation: Int,
    @SerialName("creation_date")
    val creationDate: Long,
    @SerialName("user_type")
    val userType: String,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("accept_rate")
    val acceptRate: Int? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("website_url")
    val websiteUrl: String? = null,
    @SerialName("link")
    val link: String,
    @SerialName("profile_image")
    val profileImage: String,
    @SerialName("display_name")
    val displayName: String
)

@Serializable
data class BadgeCounts(
    val bronze: Int,
    val silver: Int,
    val gold: Int
)
