package com.nadira.loanProject.loaninitiationservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadira.loanProject.loaninitiationservice.db.entity.Account
import com.nadira.loanProject.loaninitiationservice.db.entity.Loan
import com.nadira.loanProject.loaninitiationservice.dto.AddLoanDto
import org.bouncycastle.asn1.cms.CMSAttributes.contentType
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SpringBootTest
@AutoConfigureMockMvc
internal class LoanControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
) {

    @Nested
    @DisplayName("POST /loan")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostRegister {

        @Test
        fun `should add a new loan`() {
            //given
            val newLoan = AddLoanDto(account = 1, nominal = 20000000, dueDate = java.sql.Date.valueOf("2021-07-25"))

            //when
            val performPost = mockMvc.post("/add-loan") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newLoan)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                    }

        }

        @Test
        fun `should display error when user status is not active`() {
            //given
            val newLoan = AddLoanDto(account = 4, nominal = 20000000, dueDate = java.sql.Date.valueOf("2021-07-25"))

            //when
            val performPost = mockMvc.post("/add-loan") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newLoan)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isBadRequest() }
                    }

        }


        @Test
        fun `should display error when user is not exist`() {
            //given
            val newLoan = AddLoanDto(account = 100, nominal = 20000000, dueDate = java.sql.Date.valueOf("2021-07-25"))

            //when
            val performPost = mockMvc.post("/add-loan") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newLoan)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isNotFound() }
                    }

        }
    }

    @Test
    fun getAllLoan() {
    }
}