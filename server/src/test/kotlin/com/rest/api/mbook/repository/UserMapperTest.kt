package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.User
import com.rest.api.mbook.enum.Role
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired

/**
 * ユーザー情報テスト
 */
@MybatisTest
class UserMapperTest {

    @Autowired
    private lateinit var userMapper: UserMapper

    @DisplayName("ユーザー情報を1件取得するテスト")
    @Test
    fun findTest() {
        val user: User = userMapper.find(1)
        assert(user.user_name == "MB00001")
        assert(user.getRole() == Role.SYSTEM_ADMIN)
    }
}