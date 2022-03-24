package com.rest.api.mbook.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.Date

/**
 * 認証 Service
 */
@Service
class AuthorizationService {

    /**
     * 暗号化に使用するキー
     */
    val key: String = "1a1a1a1a1a1a1a1a1a1a1a1a1a1a1a1a"

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
     * @return ユーザー情報
     */
     fun getIdFromToken(token: String): String? {
        return Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(key.toByteArray(Charsets.UTF_8)))
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }
}