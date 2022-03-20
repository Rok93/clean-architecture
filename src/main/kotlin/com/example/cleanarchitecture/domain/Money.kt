package com.example.cleanarchitecture.domain

import java.math.BigInteger

data class Money(
    val amount: BigInteger,
) {
    fun negate(): Money = Money(amount.negate())

    operator fun compareTo(money: Money): Int = amount.compareTo(money.amount)

    operator fun plus(money: Money): Money = Money(amount + money.amount)

    operator fun minus(money: Money): Money = Money(amount + money.amount)


    companion object {
        val ZERO: Money = Money(BigInteger.ZERO)

        fun of(value: Long) = Money(BigInteger.valueOf(value))

        fun add(moneyA: Money, moneyB: Money): Money = Money(moneyA.amount + moneyB.amount)

        fun subtract(moneyA: Money, moneyB: Money): Money = Money(moneyA.amount - moneyB.amount)
    }
}
