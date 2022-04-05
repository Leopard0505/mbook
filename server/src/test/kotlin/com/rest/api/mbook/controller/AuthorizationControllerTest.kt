package com.rest.api.mbook.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.rest.api.mbook.entity.LoginUser
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.exception.NotFoundException
import com.rest.api.mbook.service.AuthorizationService
import com.rest.api.mbook.service.UserService
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.Calendar

/**
 * 認証Controllerのテスト
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthorizationControllerTest {

    @Rule
    val mockito: MockitoRule = MockitoJUnit.rule()

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var userService: UserService

    @MockBean
    private lateinit var authorizationService: AuthorizationService

    @DisplayName("サインインテスト")
    @Test
    fun signin() {
        val user = User(user_id = 1L, user_name = "MB00001", role_id = 1)
        `when`(userService.find("MB00001")).thenReturn(user)
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 10)
        `when`(authorizationService.generateToken(user.user_id.toString(), calendar.time)).thenReturn("abcdefghijklmnopqrstuvwxyz")
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signin")
            .header("Content-Type", "application/json")
            .content(ObjectMapper().writeValueAsString(LoginUser(username = "MB00001", password = "mb00001"))))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(user)))
    }

    @DisplayName("サインインのパスワード不正テスト")
    @Test
    fun signinValidate() {
        val exception = assertThrows<Exception> {
            val user = User(user_id = 1L, user_name = "MB00001", role_id = 1)
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/signin")
                .header("Content-Type", "application/json")
                .content(ObjectMapper().writeValueAsString(LoginUser(username = "MB00001", password = "mb11111"))))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError)
                .andExpect(MockMvcResultMatchers.content().json(ObjectMapper().writeValueAsString(user)))
        }
        assert(exception.cause?.message == "username or password is invalid.")
    }
}