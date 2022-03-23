package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.User
import org.apache.ibatis.annotations.Mapper

/**
 * ユーザー情報 Mapper
 */
@Mapper
interface UserMapper {

    /**
     * ユーザー情報を取得
     * @param user_id: Int
     * @return ユーザー情報
     */
    fun find(user_id: Int): User
}