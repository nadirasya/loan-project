package com.nadira.loanProject.loaninitiationservice.db.entity

import com.fasterxml.jackson.annotation.JsonFormat
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
        @JsonFormat(pattern = "yyyy/MM/dd")
        val dueDate: Date? = null,

        @Column
        @JsonFormat(pattern = "yyyy/MM/dd")
        val paidDate: Date? = null
)