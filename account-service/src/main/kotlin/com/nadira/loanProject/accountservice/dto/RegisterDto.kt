package com.nadira.loanProject.accountservice.dto

data class RegisterDto(
        val email: String,
        val password: String,
        val isActive: Boolean = true,
)