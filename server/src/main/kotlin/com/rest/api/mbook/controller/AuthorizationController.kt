package com.rest.api.mbook.controller

import com.rest.api.mbook.annotation.NonAuthorize
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.service.AuthorizationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Calendar


/**
 * 認証 Controller
 */
@RestController
@RequestMapping("/api/v1/auth/")
class AuthorizationController {

    @Autowired
    lateinit var authorizationService: AuthorizationService

    @PostMapping("/")
    @NonAuthorize
    fun generateToken(@RequestBody user: User): ResponseEntity<String> {
        // 加算される現在時間の取得(Calender型)
        val calendar = Calendar.getInstance()
        // 日時を加算する（10日）
        calendar.add(Calendar.DATE, 10)
        val token: String? = authorizationService.generateToken(user.user_id.toString(), calendar.time)
        return ResponseEntity.ok(token)
    }
}