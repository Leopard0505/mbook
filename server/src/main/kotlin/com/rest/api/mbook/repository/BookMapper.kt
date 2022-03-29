package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.entity.BookInfo
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
     * @param title: String?
     * @param publisher: String?
     * @return 本情報一覧
     */
    fun findAll(@Param("user_id") user_id: Int, @Param("title") title: String?, @Param("publisher") publisher: String?): List<Book>

    /**
     * 本（マスタ）情報を登録
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

    /**
     * 本情報を登録
     * @param bookInfo: BookInfo
     * @return 本情報ID
     */
    fun register(bookInfo: BookInfo): Long
}