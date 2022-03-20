package com.example.cleanarchitecture.domain

import java.time.LocalDateTime

class Account(
    val id: AccountId,
    val baselineBalance: Money,
    val activityWindow: ActivityWindow,
) {
    fun calculateBalance(): Money {
        return Money.add(
            this.baselineBalance,
            this.activityWindow.calculateBalance(this.id)
        )
    }

    fun withdraw(money: Money, targetAccountId: AccountId): Boolean {
        if (!mayWithdraw(money)) {
            return false
        }

        val withdrawal = Activity(
            this.id,
            this.id,
            targetAccountId,
            LocalDateTime.now(),
            money
        )

        this.activityWindow.addActivity(withdrawal)
        return true
    }

    private fun mayWithdraw(money: Money): Boolean {
        return Money.add(
            this.calculateBalance(),
            money.negate()
        ) > Money.ZERO
    }


    fun deposit(money: Money, sourceAccountId: AccountId) : Boolean {
        val deposit = Activity(
            this.id,
            sourceAccountId,
            this.id,
            LocalDateTime.now(),
            money
        )

        this.activityWindow.addActivity(deposit)
        return true
    }

    data class AccountId(
        val value: Long,
    )
}
