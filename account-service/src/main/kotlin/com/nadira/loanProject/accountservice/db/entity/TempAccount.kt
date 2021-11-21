package com.nadira.loanProject.accountservice.db.entity

import org.springframework.data.redis.core.RedisHash
import javax.persistence.Id

@RedisHash("account", timeToLive = 3600)
data class TempAccount (
        @Id
        private val id: String? = null,
        private val email: String? = null,
        private val valid: Boolean = false,
        private val isActive: Boolean = true,
)