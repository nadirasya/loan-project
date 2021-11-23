package com.nadira.loanProject.paymentservice.dto

import java.util.*

data class TotalPaymentResponseDto(
        val totalDelay: Long,
        val nominalToPaid: Long
)