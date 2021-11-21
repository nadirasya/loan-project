package com.nadira.loanProject.accountservice.service

import com.nadira.loanProject.accountservice.db.entity.Account
import com.nadira.loanProject.accountservice.db.entity.TempAccount
import com.nadira.loanProject.accountservice.db.repository.AccountRepository
import com.nadira.loanProject.accountservice.db.repository.TempAccountRepository
import com.nadira.loanProject.accountservice.dto.RegisterCheckDto
import com.nadira.loanProject.accountservice.dto.RegisterDto
import com.nadira.loanProject.accountservice.feignClient.OTPClient
import feign.FeignException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AccountService @Autowired constructor(
        private val accountRepository: AccountRepository,
        private val tempAccountRepository: TempAccountRepository,
){

    var logger: Logger = LoggerFactory.getLogger(AccountService::class.java)

    private fun isExistOnPostgre(email: String): Boolean {
        var accountByEmail: Account? = accountRepository.getFirstByEmail(email)
        if (accountByEmail != null) return true
        return false
    }

    private fun isExistOnRedis(email: String): Boolean {
        var tempAccountByEmail = tempAccountRepository.getFirstByEmail(email)
        if (tempAccountByEmail != null) return true
        return false
    }

    fun registerUser(registerDto: RegisterDto): ResponseEntity<*>? {
        val email: String = registerDto.email
        if (isExistOnRedis(email)) return ResponseEntity.ok().build<Any>()
        if (isExistOnPostgre(email)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exist.")

        var tempAccountByEmail = TempAccount(email = registerDto.email, valid = false)
        tempAccountRepository.save(tempAccountByEmail)

        val newAccount = Account(
                email = registerDto.email,
                password = registerDto.password,
                isActive = true

        )
        accountRepository.save(newAccount)
        return ResponseEntity.status(HttpStatus.CREATED).body("User account created successfully.")
    }

    fun setUserToNonActive(registerCheckDto: RegisterCheckDto) : ResponseEntity<*>? {
        var accountByEmail: Account? = accountRepository.getFirstByEmail(registerCheckDto.email)
                ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.")

        val account = Account (
                email = registerCheckDto.email,
                password = accountByEmail?.password,
                isActive = false
                )

        accountRepository.save(account)
        return ResponseEntity.ok().body("User status updated successfully.")
    }

}