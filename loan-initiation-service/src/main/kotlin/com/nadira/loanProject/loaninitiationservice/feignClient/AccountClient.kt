package com.nadira.loanProject.loaninitiationservice.feignClient

import com.nadira.loanProject.loaninitiationservice.db.entity.Account
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

@FeignClient(value = "account-service")
public interface AccountClient {
    @GetMapping("/user/{id}")
    fun getUserById(@PathVariable id: Long): Account;
}
