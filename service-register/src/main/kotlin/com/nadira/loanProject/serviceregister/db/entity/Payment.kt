package com.nadira.loanProject.serviceregister.db.entity

import java.util.*

@Entity
data class Payment (
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