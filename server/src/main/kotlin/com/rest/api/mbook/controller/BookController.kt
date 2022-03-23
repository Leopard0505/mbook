package com.rest.api.mbook.controller

import com.rest.api.mbook.annotation.Authorize
import com.rest.api.mbook.annotation.NonAuthorize
import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * 本情報 Controller
 */
@RestController
@RequestMapping("/api/v1/books")
class BookController {

    @Autowired
    lateinit var bookService: BookService

    /**
     * 本情報一覧を取得
     */
    @GetMapping("/")
    @Authorize
    fun findAll(): List<Book> {
        return bookService.findAll(1)
    }
}
