package com.nadira.loanProject.accountservice.db.repository

import com.nadira.loanProject.accountservice.db.entity.TempAccount
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TempAccountRepository : CrudRepository<TempAccount?, String?> {
    fun getFirstByEmail(email: String?): TempAccount?
}