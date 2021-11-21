package com.nadira.loanProject.accountservice.controller
import com.nadira.loanProject.accountservice.dto.RegisterCheckDto
import com.nadira.loanProject.accountservice.dto.RegisterDto
import com.nadira.loanProject.accountservice.service.AccountService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.NoSuchElementException

@RestController
class AccountController @Autowired constructor(private val accountService: AccountService) {

    var logger: Logger = LoggerFactory.getLogger(AccountController::class.java)

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
            ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    fun register(@RequestBody registerDto: RegisterDto): ResponseEntity<*>? {
        return accountService.registerUser(registerDto)
    }

    @PostMapping("/set-user-non-active")
    @ResponseStatus(HttpStatus.OK)
    fun setUserToNonActive(@RequestBody registerCheckDto: RegisterCheckDto): ResponseEntity<*>? {
        return accountService.setUserToNonActive(registerCheckDto)
    }

}