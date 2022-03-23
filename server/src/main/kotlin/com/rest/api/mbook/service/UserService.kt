package com.rest.api.mbook.service

import com.rest.api.mbook.entity.User
import com.rest.api.mbook.exception.NotFoundException
import com.rest.api.mbook.repository.UserMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Objects

/**
 * ユーザー情報 Service
 */
@Service
class UserService {

    @Autowired
    lateinit var userMapper: UserMapper

    /**
     * ユーザー情報を取得
     * @param user_id: Int
     * @return ユーザー情報
     */
    fun find(user_id: Int): User {
        val user = userMapper.find(user_id)
        if (Objects.isNull(user)) {
            throw NotFoundException(String.format("user_id: %s is not found.", user_id))
        }
        return user
    }
}