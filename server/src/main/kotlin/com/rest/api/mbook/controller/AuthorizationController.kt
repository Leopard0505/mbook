package com.rest.api.mbook.controller

import com.rest.api.mbook.annotation.NonAuthorize
import com.rest.api.mbook.entity.LoginUser
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.service.AuthorizationService
import com.rest.api.mbook.service.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
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

    /**
     * logger ロガー
     */
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    lateinit var authorizationService: AuthorizationService

    @Autowired
    lateinit var userService: UserService

    /**
     * アクセストークンを生成
     */
    @PostMapping("/")
    @NonAuthorize
    fun generateToken(@RequestBody user: User): ResponseEntity<String> {
        val token: String? = generateToken(user.user_id)
        return ResponseEntity.ok(token)
    }

    /**
     * アクセストークンを生成
     */
    @PostMapping("/token")
    @NonAuthorize
    fun generateToken(@RequestBody request: LoginUser): ResponseEntity<User> {
        logger.info("===========================")
        logger.info(String.format("username: [%s]", request.username))
        logger.info(String.format("password: [%s]", "*******"))
        logger.info("===========================")
        // ユーザー名、パスワード確認
        request.validate()
        val user = userService.find(request.username)
        val token: String? = generateToken(user.user_id)
        user.token = token
        return ResponseEntity.ok(user)
    }

    /**
     * アクセストークンを生成
     * @param userId: Long
     * @return アクセストークン
     */
    private fun generateToken(userId: Long): String? {
        // 加算される現在時間の取得(Calender型)
        val calendar = Calendar.getInstance()
        // 日時を加算する（10日）
        calendar.add(Calendar.DATE, 10)
        return authorizationService.generateToken(userId.toString(), calendar.time)
    }
}