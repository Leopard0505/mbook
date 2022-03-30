package com.rest.api.mbook.service

import com.rest.api.mbook.entity.User
import com.rest.api.mbook.enum.Role
import com.rest.api.mbook.exception.NotFoundException
import com.rest.api.mbook.repository.UserMapper
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.`when`
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest


/**
 * ユーザー情報テスト Service
 */
@MybatisTest
class UserServiceTest {

    @Rule
    val mockito: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var mockUserMapper: UserMapper

    @InjectMocks
    private lateinit var userService: UserService

    @DisplayName("ユーザー情報をユーザーIDで取得するテスト")
    @Test
    fun find() {
        `when`(mockUserMapper.find(1)).thenReturn(User(1L, "MB00001", 1))
        val user = userService.find(1)
        assert(user.user_name == "MB00001")
        assert(user.getRole() == Role.SYSTEM_ADMIN)
    }

    @DisplayName("ユーザー情報をユーザー名で取得するテスト")
    @Test
    fun findByName() {
        `when`(mockUserMapper.findByName("MB00001")).thenReturn(User(1L, "MB00001", 1))
        val user = userService.find("MB00001")
        assert(user.user_name == "MB00001")
        assert(user.getRole() == Role.SYSTEM_ADMIN)
    }

    @DisplayName("ユーザーID検索でNotFoundExceptionになるテスト")
    @Test
    fun notFoundException() {
        val exception = assertThrows<NotFoundException> {
            `when`(mockUserMapper.find(2)).thenReturn(null)
            userService.find(2)
        }
        assert(exception.message == String.format("user_id: %s is not found.", 2))
    }

    @DisplayName("ユーザー名検索でNotFoundExceptionになるテスト")
    @Test
    fun notFoundExceptionByName() {
        val exception = assertThrows<NotFoundException> {
            `when`(mockUserMapper.findByName("MB11111")).thenReturn(null)
            userService.find("MB11111")
        }
        assert(exception.message == String.format("user_name: %s is not found.", "MB11111"))
    }
}