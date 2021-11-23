package com.nadira.loanProject.paymentservice.feignClient

import com.nadira.loanProject.paymentservice.db.entity.Loan
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@FeignClient(value = "loan-initiation-service")
public interface LoanClient {
    @GetMapping("/loan/{id}")
    fun getLoanById(@PathVariable id: Long): Loan;
}