package com.nadira.loanProject.loaninitiationservice.db.entity

//import lombok.Data
import javax.persistence.*

//@Data
@Entity
data class Account (
        @Id
        @GeneratedValue
        val id: Long? = null,

        @Column(nullable = false)
        val email: String? = null,

        @Column(nullable = false)
        val password: String? = null,

        @Column(nullable = false)
        val isActive: Boolean? = null,
) {
    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, mappedBy = "account")
    private val loans = mutableListOf<Loan>()
}