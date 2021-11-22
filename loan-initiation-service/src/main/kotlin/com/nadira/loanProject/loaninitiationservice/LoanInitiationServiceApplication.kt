package com.nadira.loanProject.loaninitiationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class LoanInitiationServiceApplication

fun main(args: Array<String>) {
	runApplication<LoanInitiationServiceApplication>(*args)
}
