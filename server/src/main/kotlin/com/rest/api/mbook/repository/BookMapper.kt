package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.Book
import org.apache.ibatis.annotations.Mapper

/**
 * 本情報 Mapper
 */
@Mapper
interface BookMapper {

    /**
     * 本情報一覧を取得
     * @param user_id: Int
     * @return 本情報一覧
     */
    fun findAll(user_id: Int): List<Book>
}