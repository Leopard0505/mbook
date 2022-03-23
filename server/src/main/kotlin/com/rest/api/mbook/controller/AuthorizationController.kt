package com.rest.api.mbook.controller

import com.rest.api.mbook.annotation.NonAuthorize
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.interceptor.AuthorizationInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.text.SimpleDateFormat
import java.util.*


/**
 * 認証 Controller
 */
@RestController
@RequestMapping("/api/v1/auth/")
class AuthorizationController {

    @Autowired
    lateinit var authorizationInterceptor: AuthorizationInterceptor

    @PostMapping("/")
    @NonAuthorize
    fun generateToken(@RequestBody user: User): ResponseEntity<String> {
        // 加算される現在時間の取得(Calender型)
        val calendar = Calendar.getInstance()
        // 日時を加算する
        calendar.add(Calendar.DATE, 10)
        // Calendar型の日時をDate型に変換
        val token: String? = authorizationInterceptor.generateToken(user.user_id.toString(), calendar.time)
        return ResponseEntity.ok(token)
    }
}