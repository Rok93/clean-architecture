package com.example.cleanarchitecture.domain

import java.time.LocalDateTime

class ActivityWindow(
    private val activities: MutableList<Activity>,
) {
    constructor(vararg activities: Activity) : this(activities.toMutableList())

    fun getStartTimestamp(): LocalDateTime {
        val minActivity = activities.minByOrNull { it.timestamp } ?: throw IllegalArgumentException()
        return minActivity.timestamp
    }

    fun getEndTimestamp(): LocalDateTime {
        val maxActivity = activities.maxByOrNull { it.timestamp } ?: throw IllegalArgumentException()
        return maxActivity.timestamp
    }

    fun calculateBalance(accountId: Account.AccountId): Money {
        val depositBalance = activities.filter { it.targetAccountId == accountId }
            .map { it.money }
            .reduce { moneyA, moneyB -> moneyA + moneyB }

        val withdrawalBalance = activities.filter { it.sourceAccountId == accountId }
            .map { it.money }
            .reduce { moneyA, moneyB -> moneyA + moneyB }

        return depositBalance + withdrawalBalance
    }

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }
}
