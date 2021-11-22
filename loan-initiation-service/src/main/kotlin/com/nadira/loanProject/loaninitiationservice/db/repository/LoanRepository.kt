package com.nadira.loanProject.loaninitiationservice.db.repository

import com.nadira.loanProject.loaninitiationservice.db.entity.Loan
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : JpaRepository<Loan?, Long?> {
}