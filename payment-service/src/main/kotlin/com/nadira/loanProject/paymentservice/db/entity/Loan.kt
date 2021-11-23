package com.nadira.loanProject.paymentservice.db.entity

import java.util.*
import javax.persistence.*

@Entity
data class Loan (
        @Id
        @GeneratedValue
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "accountId")
        val account: Account,

        @Column(nullable = false)
        val nominal: Int,

        @Column(nullable = false)
        val dueDate: Date,

        @Column
        val paidDate: Date? = null
)