package com.nadira.loanProject.loaninitiationservice.controller

import com.nadira.loanProject.loaninitiationservice.db.entity.Loan
import com.nadira.loanProject.loaninitiationservice.dto.AddLoanDto
import com.nadira.loanProject.loaninitiationservice.service.LoanService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class LoanController @Autowired constructor(private val loanService: LoanService) {

    var logger: Logger = LoggerFactory.getLogger(LoanController::class.java)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
            ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @PostMapping("/add-loan")
    @ResponseStatus(HttpStatus.OK)
    fun addNewLoan(@RequestBody addLoanDto: AddLoanDto): ResponseEntity<*>? {
        return loanService.addNewLoan(addLoanDto)
    }

    @GetMapping("/loan")
    @ResponseStatus(HttpStatus.OK)
    fun getAllLoan(): MutableList<Loan?> {
        return loanService.getAllLoan()
    }


}