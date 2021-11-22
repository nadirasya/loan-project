package com.nadira.loanProject.loaninitiationservice.dto

import com.nadira.loanProject.loaninitiationservice.db.entity.Account
import java.util.*
import javax.persistence.*

data class AddLoanDto (
        val account: Long,
        val nominal: Int,
        val dueDate: Date,
)