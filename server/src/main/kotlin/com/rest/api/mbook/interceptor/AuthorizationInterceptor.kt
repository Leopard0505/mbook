package com.rest.api.mbook.interceptor

import com.rest.api.mbook.annotation.Authorize
import com.rest.api.mbook.annotation.NonAuthorize
import com.rest.api.mbook.entity.User
import com.rest.api.mbook.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.AnnotationUtils
import org.springframework.http.HttpStatus
import org.springframework.web.method.HandlerMethod
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.HandlerInterceptor
import java.lang.reflect.Method
import java.util.Date
import java.util.Objects
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * 認証 Interceptor
 */
class AuthorizationInterceptor: HandlerInterceptor {

    /**
     * logger ロガー
     */
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    /**
     * 暗号化に使用するキー
     */
    val key: String = "1a1a1a1a1a1a1a1a1a1a1a1a1a1a1a1a"

    @Autowired
    lateinit var userService: UserService

    /**
     * JsonWebTokenを生成
     * @param id: String ユーザー情報
     * @param expiration: Date 有効期限
     * @return JsonWebToken
     */
    fun generateToken(id: String?, expiration: Date?): String? {
        return Jwts.builder()
            .setSubject(id)
            .setExpiration(expiration)
            .signWith(Keys.hmacShaKeyFor(key.toByteArray(Charsets.UTF_8)))
            .compact()
    }

    /**
     * JsonWebTokenを解析
     * @param token: String? JsonWebToken
     * @param key: String 暗号化に使用したキー
     * @return ユーザー情報
     */
    private fun getIdFromToken(token: String?, key: String): String? {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(key.toByteArray(Charsets.UTF_8)))
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    /**
     * 認可処理
     * @param request: HttpServletRequest
     * @return 認可結果
     */
    private fun authorize(request: HttpServletRequest): Boolean {
        // Authorizationの値を取得
        val authorization: String = request.getHeader("Authorization")
        if (authorization == null || authorization.isEmpty()) {
            return false
        }
        // Bearer tokenの形式であることをチェック
        if (authorization.indexOf("Bearer ") != 0) {
            return false
        }
        // トークンを取得しidを取得する
        val token = authorization.substring(7)
        val id = getIdFromToken(token, key)

        // idの検証を行う
        if (Objects.isNull(id)) {
            return false
        }
        val user: User = userService.find(Integer.parseInt(id))
        logger.info("===========================")
        logger.info(String.format("ユーザーID： %s", user.user_id))
        logger.info(String.format("ユーザー名： %s", user.user_name))
        logger.info(String.format("ユーザー権限： %s", user.getRole().name))
        logger.info("===========================")
        // コントローラでユーザー情報を受け取れるようにuser属性として渡す
        request.setAttribute("user", user)
        return true
    }

    /**
     * コントローラー前に実行される。
     */
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // キャスト判定
        if (handler !is HandlerMethod) {
            return true
        }

        // 実行されるメソッドを取得
        val method: Method = handler.method
        // @NonAuthorizeが付与されているか確認
        if (AnnotationUtils.findAnnotation(method, NonAuthorize::class.java) != null) {
            // 付与されている場合は認可せずに終了
            return true
        }

        // メソッドに対応するControllerを取得
        val controller = method.declaringClass
        // Controllerまたはメソッドに@Authorizeが付与されているか確認
        if (AnnotationUtils.findAnnotation(controller, Authorize::class.java) != null
            || AnnotationUtils.findAnnotation(method, Authorize::class.java) != null
        ) {
            // 付与されている場合は認可処理を実行
            if (!authorize(request)) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN)
            }
        }
        return true
    }
}