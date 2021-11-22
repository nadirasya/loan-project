package com.nadira.loanProject.loaninitiationservice.db.repository

import com.nadira.loanProject.loaninitiationservice.db.entity.TempLoan
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TempAccountRepository : CrudRepository<TempLoan?, String?> {
}