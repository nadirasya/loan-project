package com.nadira.loanProject.paymentservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadira.loanProject.paymentservice.dto.PaymentDto
import com.nadira.loanProject.paymentservice.dto.TotalPaymentDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.sql.Date

@SpringBootTest
@AutoConfigureMockMvc
internal class PaymentControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
) {

    @Test
    fun postPayment() {
    }

    @Nested
    @DisplayName("POST /total-payment")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostTotalPayment {

        @Test
        fun `should return a correct amount of nominal to be paid`() {
            //given
            val totalPayment = TotalPaymentDto(dueDate = java.sql.Date.valueOf("2021-01-01"), paidDate = java.sql.Date.valueOf("2021-02-02"))

            //when
            val performPost = mockMvc.post("/total-payment") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(totalPayment)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status {
                            isOk()
                            content { contentType(MediaType.APPLICATION_JSON) }
                            jsonPath("$.totalDelay"){value(32)}
                            jsonPath("$.nominalToPaid"){value(32000)}
                        }
                    }

        }
    }

    @Nested
    @DisplayName("POST /payment")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostPayment {

        @Test
        fun `should be able to paid the correct amount of loan`() {
            //given
            val payment = PaymentDto(loan=13, nominalPaid= 11000,paidDate = Date.valueOf("2021-02-02"))

            //when
            val performPost = mockMvc.post("/payment") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(payment)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status {
                            isCreated()
                        }
                    }

        }

        @Test
        fun `should not be able to paid the loan when the amount is incorrect`() {
            //given
            val payment = PaymentDto(loan=13, nominalPaid= 10000,paidDate = Date.valueOf("2021-02-02"))

            //when
            val performPost = mockMvc.post("/payment") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(payment)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status {
                            isBadRequest()
                        }
                    }

        }
    }
//    @Test
//    fun getTotalPayment() {
//    }
}