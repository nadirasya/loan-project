package com.nadira.loanProject.paymentservice.service

import com.nadira.loanProject.paymentservice.db.entity.Loan
import com.nadira.loanProject.paymentservice.db.entity.Payment
import com.nadira.loanProject.paymentservice.db.repository.PaymentRepository
import com.nadira.loanProject.paymentservice.dto.PaymentDto
import com.nadira.loanProject.paymentservice.dto.TotalPaymentDto
import com.nadira.loanProject.paymentservice.dto.TotalPaymentResponseDto
import com.nadira.loanProject.paymentservice.feignClient.LoanClient
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.sql.Date
import java.util.*

@Service
class PaymentService @Autowired constructor(
        private val paymentRepository: PaymentRepository,
        private val loanClient: LoanClient
){
    var logger: Logger = LoggerFactory.getLogger(PaymentService::class.java)

    fun getAllPayment(): MutableList<Payment?> {
        return paymentRepository.findAll()
    }

    fun getPaymentById(id: Long): Optional<Payment?> {
        return paymentRepository.findById(id)
    }

    //room for improvement
    private fun calculateTotalPenalty(totalDays: Long): Long {
        return totalDays * 1000;
    }

    fun getTotalPayment(totalPaymentDto: TotalPaymentDto): TotalPaymentResponseDto {
        val totalDelay: Long = totalPaymentDto.paidDate.time - totalPaymentDto.dueDate.time

        val seconds: Long = totalDelay / 1000
        val minutes = seconds / 60
        val hours = minutes / 60
        val days = hours / 24

        return TotalPaymentResponseDto(totalDelay = days, nominalToPaid = calculateTotalPenalty(days));

    }

    fun addPayment(payment: PaymentDto): ResponseEntity<*>? {
        val loan: Loan = loanClient.getLoanById(payment.loan);
        if(loan == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan account not found.")
        if(loan.paidDate != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Loan has been paid.")

        val totalPayment =TotalPaymentDto(dueDate = Date.valueOf(loan.dueDate.toString()), paidDate = Calendar.getInstance().time)

        val totalPaymentResponse: TotalPaymentResponseDto = getTotalPayment(totalPayment)
        if(totalPaymentResponse.nominalToPaid.toInt() != payment.nominalPaid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Amount of the payment is incorrect")
        }

        val newPayment = Payment(loan = loan, nominalPaid = payment.nominalPaid, paidDate = Calendar.getInstance().time)
        paymentRepository.save(newPayment);

        return ResponseEntity.status(HttpStatus.CREATED).body("Your loan has been paid successfully.")
    }

}
