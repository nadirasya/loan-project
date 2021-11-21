package com.nadira.loanProject.accountservice.db.entity

//import lombok.Data
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

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
    val isActive: Boolean? = null
)