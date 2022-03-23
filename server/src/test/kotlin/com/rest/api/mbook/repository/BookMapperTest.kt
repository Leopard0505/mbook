package com.rest.api.mbook.repository

import com.rest.api.mbook.entity.Book
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
}