package com.example.cleanarchitecture.domain

import java.time.LocalDateTime

data class Activity(
    val ownerAccountId: Account.AccountId,
    val sourceAccountId: Account.AccountId,
    val targetAccountId: Account.AccountId,
    val timestamp: LocalDateTime,
    val money: Money,
) {
    var id: ActivityId? = null

    data class ActivityId(
        val value: Long,
    )
}
