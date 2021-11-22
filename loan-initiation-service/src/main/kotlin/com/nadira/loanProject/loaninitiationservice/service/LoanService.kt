package com.nadira.loanProject.loaninitiationservice.service

import com.nadira.loanProject.loaninitiationservice.db.entity.Account
import com.nadira.loanProject.loaninitiationservice.db.entity.Loan
import com.nadira.loanProject.loaninitiationservice.db.repository.LoanRepository
import com.nadira.loanProject.loaninitiationservice.dto.AddLoanDto
import com.nadira.loanProject.loaninitiationservice.feignClient.AccountClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class LoanService @Autowired constructor(
    private val loanRepository: LoanRepository,
    private val tempLoanRepository: LoanRepository,
    private val accountClient: AccountClient
){

    var logger: Logger = LoggerFactory.getLogger(LoanService::class.java)

    fun addNewLoan(addLoanDto: AddLoanDto): ResponseEntity<*>?{
        val account: Account = accountClient.getUserById(addLoanDto.account);
        if(account == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User account not found.")
        val loan = Loan(account=account, nominal = addLoanDto.nominal, dueDate = addLoanDto.dueDate)
        tempLoanRepository.save(loan);
        loanRepository.save(loan);
        return ResponseEntity.status(HttpStatus.CREATED).body("Loan created successfully.")
    }

    fun getAllLoan(): MutableList<Loan?> {
        return loanRepository.findAll();
    }
}