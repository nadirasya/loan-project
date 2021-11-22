package com.nadira.loanProject.accountservice.db.entity

import java.util.*
import javax.persistence.*

@Entity
data class Loan (
        @Id
        @GeneratedValue
        val id: Long? = null,

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "accountId")
        val account: Account? = null,

        @Column(nullable = false)
        val nominal: Int? = null,

        @Column(nullable = false)
        val dueDate: Date? = null,

        @Column
        val paidDate: Date? = null
)