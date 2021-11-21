package com.nadira.loanProject.accountservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.nadira.loanProject.accountservice.db.entity.Account
import com.nadira.loanProject.accountservice.dto.RegisterCheckDto
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
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class AccountControllerTest @Autowired constructor(
        val mockMvc: MockMvc,
        val objectMapper: ObjectMapper,
) {

    @Nested
    @DisplayName("POST /register")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostRegister {

        @Test
        fun `should create a new account`(){
            //given
            val newAccount = Account(email="new_account@mail.com", password = "password123", isActive = true)

            //when
            val performPost = mockMvc.post("/register") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(newAccount)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                    }

        }
        @Test
        fun `should return error message when user already exist`() {
            //given
            val alreadyExistAccount = Account(email = "test@mail.com", password = "password123", isActive = true)

            //when
            val performPost = mockMvc.post("/register") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(alreadyExistAccount)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isBadRequest() }
                    }
        }
    }

    @Nested
    @DisplayName("POST /set-user-non-active")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PostUserNonActive {

        @Test
        fun `should update status of an existing account`(){
            //given
            val existingEmail = RegisterCheckDto(email="new_account@mail.com")

            //when
            val performPost = mockMvc.post("/set-user-non-active") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(existingEmail)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                    }

        }

        @Test
        fun `should return bad request when updating status of a non-existing account`(){
            //given
            val nonExistingEmail = RegisterCheckDto(email="nonexisting@mail.com")

            //when
            val performPost = mockMvc.post("/set-user-non-active") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(nonExistingEmail)
            }

            //then
            performPost
                    .andDo { print() }
                    .andExpect {
                        status { isNotFound() }
                    }

        }
    }

}