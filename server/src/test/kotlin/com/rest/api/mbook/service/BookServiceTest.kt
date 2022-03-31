package com.rest.api.mbook.service

import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.entity.BookInfo
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.repository.BookMapper
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest

/**
 * 本情報Service テスト
 */
@MybatisTest
class BookServiceTest {

    @Rule
    val mockito: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var bookMapper: BookMapper

    @InjectMocks
    private lateinit var bookService: BookService

    @DisplayName("本情報一覧を取得するテスト")
    @Test
    fun findAll() {
        // 検索用パラメータ
        val user_id = 1
        val title = null
        val publisher = null

        // 結果用
        val bookInfos: List<BookInfo> = listOf(
            BookInfo(book_info_id = 1, book_id = 1, title = "ノーゲーム・ノーライフ（1）"),
            BookInfo(book_info_id = 2, book_id = 1, title = "ノーゲーム・ノーライフ（2）")
        )
        val books: List<Book> = listOf(
            Book(book_id = 1, title = "ノーゲーム・ノーライフ", bookInfos = bookInfos),
        )

        // モック
        `when`(bookMapper.findAll(user_id, title, publisher)).thenReturn(books)

        // テスト
        val resultBooks = bookService.findAll(user_id, title, publisher)
        assert(resultBooks.size == 1)
        assert(resultBooks[0].bookInfos.size == 2)
    }

    @DisplayName("本（マスタ）情報を登録するテスト")
    @Test
    fun create() {
        // パラメータ
        val user = User(user_id = 1, user_name = "MB00001")
        val book = Book(title = "トリニティセブン", original_author = "サイトウケンジ", drawer = "奈央晃徳", publisher = "富士見書房")

        // モック
        `when`(bookMapper.create(book)).thenReturn(1)
        `when`(bookMapper.createUserBooks(user, book)).thenReturn(3)

        // テスト
        bookService.create(user, book)
        assert(true)
    }

    @DisplayName("本情報を登録するテスト")
    @Test
    fun register() {
        // パラメータ
        val user = User(user_id = 1, user_name = "MB00001")
        val bookInfo = BookInfo(title = "賭ケグルイ（15）", release_date = "2021-10-21", special_edition = false)

        // モック
        `when`(bookMapper.register(bookInfo)).thenReturn(25)

        // テスト
        bookService.register(user, bookInfo)
        assert(true)
    }
}