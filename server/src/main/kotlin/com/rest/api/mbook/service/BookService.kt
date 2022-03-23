package com.rest.api.mbook.service

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.repository.BookMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 本情報 Service
 */
@Service
class BookService() {

    @Autowired
    lateinit var bookMapper: BookMapper

    /**
     * 本情報一覧を取得
     */
    fun findAll(user_id: Int): List<Book> {
        return bookMapper.findAll(user_id)
    }
}