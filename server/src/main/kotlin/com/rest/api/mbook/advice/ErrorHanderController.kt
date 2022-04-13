package com.rest.api.mbook.advice

import com.rest.api.mbook.entity.ErrorResponse
import com.rest.api.mbook.exception.NotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException


/**
 * コントローラーエラーハンドリングクラス
 */
@RestControllerAdvice
class ErrorHanderController {

    /**
     * logger ロガー
     */
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * 認可エラーハンドリング
     * レスポンスステータス: 403
     */
    @ExceptionHandler(ResponseStatusException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun handleException(e: ResponseStatusException): ResponseEntity<ErrorResponse>? {
        logger.error("ResponseStatusException:", e.message)
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                             .body(ErrorResponse("notAuthorized", "The request was not authorized."))
    }

    /**
     * API・データが見つからないエラーハンドリング
     * レスポンスステータス: 404
     */
    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handlerException(e: NotFoundException): ResponseEntity<ErrorResponse>? {
        logger.error("NotFoundException: ", e.message)
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(ErrorResponse("notFound", "The request was not found."))
    }

    /**
     * 上記までにキャッチできなかったエラーハンドリング
     * レスポンスステータス: 500
     */
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse>? {
        logger.error("Exception:", e.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorResponse("systemError", "System error occurred."))
    }
}