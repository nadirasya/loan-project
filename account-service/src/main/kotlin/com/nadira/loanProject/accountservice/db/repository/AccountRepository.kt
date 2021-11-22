package com.nadira.loanProject.accountservice.db.repository

import com.nadira.loanProject.accountservice.db.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account?, Long?> {
    fun getFirstByEmail(Email: String?): Account?;
}