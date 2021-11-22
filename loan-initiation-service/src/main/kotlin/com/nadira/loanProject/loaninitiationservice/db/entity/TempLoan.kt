package com.nadira.loanProject.loaninitiationservice.db.entity

import org.springframework.data.redis.core.RedisHash
import java.util.*
import javax.persistence.*

@RedisHash("loan", timeToLive = 3600)
data class TempLoan (
        @Id
        val id: Long? = null,
        val account: Account? = null,
        val nominal: Int? = null,
        val dueDate: Date? = null,
        val paidDate: Date? = null

)