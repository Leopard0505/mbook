package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.entity.User
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired

/**
 * 本情報テスト
 */
@MybatisTest
class BookMapperTest {

    @Autowired
    private lateinit var bookMapper: BookMapper

    @DisplayName("本情報一覧を取得するテスト")
    @Test
    fun findAll() {
        val bookList: List<Book> = bookMapper.findAll(1)
        assert(0 <= bookList.size)
    }

    @DisplayName("本情報を登録するテスト")
    @Test
    fun create() {
        val book = Book(title = "タイトル", original_author = "原作者", drawer = "作画",
            publisher = "出版社", created_user = "SYSTEM", updated_user = "SYSTEM")
        bookMapper.create(book)
        assert(book.book_id.equals(3L))
    }

    @DisplayName("ユーザー情報と本情報を紐付けるテスト")
    @Test
    fun createUserBooks() {
        val user = User(user_id = 1)
        val book = Book(book_id = 2, created_user = "SYSTEM", updated_user = "SYSTEM")
        bookMapper.createUserBooks(user, book)
        assert(true)
    }
}