package com.rest.api.mbook.controller

import com.rest.api.mbook.annotation.Authorize
import com.rest.api.mbook.entity.Book
import com.rest.api.mbook.entity.BookInfo
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.service.BookService
import com.rest.api.mbook.service.CsvFileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.servlet.http.HttpServletRequest

/**
 * 本情報 Controller
 */
@RestController
@RequestMapping("/api/v1/books")
class BookController {

    @Autowired
    lateinit var bookService: BookService

    @Autowired
    lateinit var csvFileService: CsvFileService

    /**
     * 本情報一覧を取得
     */
    @GetMapping("/")
    @Authorize
    fun findAll(request: HttpServletRequest,
                @RequestParam("title", required = false) title: String?,
                @RequestParam("publisher", required = false) publisher: String?): ResponseEntity<List<Book>> {
        // AuthorizationInterceptorで渡したuser属性を受け取る
        val user = request.getAttribute("user") as User
        val bookList = bookService.findAll(user.user_id.toInt(), title, publisher)
        return ResponseEntity.ok().body(bookList)
    }

    /**
     * 本(マスタ)情報を登録
     */
    @PostMapping("/create")
    @Authorize
    fun create(request: HttpServletRequest, @RequestBody book: Book): ResponseEntity<Book> {
        // AuthorizationInterceptorで渡したuser属性を受け取る
        val user = request.getAttribute("user") as User
        bookService.create(user, book)
        return ResponseEntity.ok(book)
    }

    /**
     * 本情報を登録
     */
    @PostMapping("/{id:[0-9]}/create")
    @Authorize
    fun register(request: HttpServletRequest, @PathVariable("id") id: Int, @RequestBody bookInfo: BookInfo): ResponseEntity<BookInfo> {
        // AuthorizationInterceptorで渡したuser属性を受け取る
        val user = request.getAttribute("user") as User
        bookInfo.book_id = id
        bookService.register(user, bookInfo)
        return ResponseEntity.ok(bookInfo)
    }

    @PostMapping("/upload")
    @Authorize
    fun upload(request: HttpServletRequest, @RequestParam("file") file: MultipartFile): ResponseEntity<List<Book>> {
        // AuthorizationInterceptorで渡したuser属性を受け取る
        val user = request.getAttribute("user") as User
        val optionalCsvFileData = csvFileService.read(file)
        val csvFileData = optionalCsvFileData.get()
        val bookList = ArrayList<Book>()
        for (book in csvFileData) {
            bookService.create(user, book)
            bookList.add(book)
        }
        return ResponseEntity.ok(bookList)
    }
}
