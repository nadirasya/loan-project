package com.nadira.loanProject.paymentservice.dto

import java.util.*

data class TotalPaymentDto(
        val dueDate: Date,
        val paidDate: Date
)