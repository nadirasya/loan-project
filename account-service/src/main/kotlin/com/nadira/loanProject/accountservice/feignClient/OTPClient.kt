package com.nadira.loanProject.accountservice.feignClient

import com.nadira.loanProject.accountservice.dto.RegisterCheckDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(value = "OTP-SERVICE")
interface OTPClient {
    @PostMapping("/request")
    fun requestOTP(@RequestBody registerCheckDto: RegisterCheckDto?): ResponseEntity<*>?
}