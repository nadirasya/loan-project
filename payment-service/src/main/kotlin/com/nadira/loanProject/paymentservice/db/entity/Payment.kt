package com.nadira.loanProject.paymentservice.db.entity

//import lombok.Data
import java.util.*
import javax.persistence.*

//@Data
@Entity
data class Payment (
        @Id
        @GeneratedValue
        val id: Long? = null,

        @OneToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "loanId")
        val loan: Loan? = null,

        @Column(nullable = false)
        val nominalPaid: Int? = null,

        @Column
        val paidDate: Date? = null
)