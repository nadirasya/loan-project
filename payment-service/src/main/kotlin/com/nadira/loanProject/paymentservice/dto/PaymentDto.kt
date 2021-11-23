package com.nadira.loanProject.paymentservice.dto

import java.util.*

data class PaymentDto (
        val loan: Long,
        val nominalPaid: Int,
        val paidDate: Date
)