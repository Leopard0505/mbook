package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.entity.User
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

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

    /**
     * 本情報を登録
     * @param book: Book
     * @return 本情報ID
     */
    fun create(book: Book): Long

    /**
     * ユーザー情報と本情報を紐付ける
     * @param user: User
     * @param book: Book
     */
    fun createUserBooks(@Param("user") user: User, @Param("book") book: Book): Long
}