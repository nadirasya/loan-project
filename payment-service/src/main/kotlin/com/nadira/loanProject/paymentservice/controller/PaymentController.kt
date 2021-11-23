package com.nadira.loanProject.paymentservice.controller
import com.nadira.loanProject.paymentservice.db.entity.Payment
import com.nadira.loanProject.paymentservice.dto.PaymentDto
import com.nadira.loanProject.paymentservice.dto.TotalPaymentDto
import com.nadira.loanProject.paymentservice.dto.TotalPaymentResponseDto
import com.nadira.loanProject.paymentservice.service.PaymentService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class PaymentController @Autowired constructor(private val paymentService: PaymentService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
            ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    fun getAllPayment(): MutableList<Payment?> {
        return paymentService.getAllPayment()
    }

    @GetMapping("/payment/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getPaymentById(@PathVariable id: Long): Optional<Payment?> {
        return paymentService.getPaymentById(id)
    }

    @PostMapping("/payment")
    @ResponseStatus(HttpStatus.OK)
    fun postPayment(@RequestBody paymentDto: PaymentDto): ResponseEntity<*>? {
        return paymentService.addPayment(paymentDto)
    }

    @PostMapping("/total-payment")
    @ResponseStatus(HttpStatus.OK)
    fun getTotalPayment(@RequestBody totalPaymentDto: TotalPaymentDto): TotalPaymentResponseDto {
        return paymentService.getTotalPayment(totalPaymentDto)
    }

}