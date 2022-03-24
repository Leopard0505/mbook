package com.rest.api.mbook.service

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.entity.BookInfo
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.repository.BookMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 本情報 Service
 */
@Service
class BookService {

    @Autowired
    lateinit var bookMapper: BookMapper

    /**
     * 本情報一覧を取得
     */
    fun findAll(user_id: Int): List<Book> {
        return bookMapper.findAll(user_id)
    }

    /**
     * 本（マスタ）情報を登録
     * @param user: User
     * @param book: Book
     * @return 本情報ID
     */
    @Transactional
    fun create(user: User, book: Book): Long {
        book.created_user = user.user_name
        book.updated_user = user.user_name
        bookMapper.create(book)
        bookMapper.createUserBooks(user, book)
        return book.book_id
    }

    /**
     * 本情報を登録
     * @param user: User
     * @param bookInfo: BookInfo
     * @return 本情報ID
     */
    fun register(user: User, bookInfo: BookInfo): Long {
        bookInfo.created_user = user.user_name
        bookInfo.updated_user = user.user_name
        bookMapper.register(bookInfo)
        return bookInfo.book_info_id
    }
}