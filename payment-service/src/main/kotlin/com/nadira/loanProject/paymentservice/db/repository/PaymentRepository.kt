package com.nadira.loanProject.paymentservice.db.repository

import com.nadira.loanProject.paymentservice.db.entity.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : JpaRepository<Payment?, Long?> {
}