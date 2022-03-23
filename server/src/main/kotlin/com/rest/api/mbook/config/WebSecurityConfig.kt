package com.rest.api.mbook.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest

/**
 * Spring Securityの設定クラス
 */
@Configuration
class WebSecurityConfig: WebSecurityConfigurerAdapter() {

    /**
     * HttpSecurity向け設定
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {

        // 他の設定がここらにあるはず
        /**
         * CSRF適用URL判断クラス
         */
        val csrfRequestMatcher: RequestMatcher = object : RequestMatcher {
            private val disabledRequestMatcher = AntPathRequestMatcher("/api/**")
            override  fun matches(request: HttpServletRequest): Boolean {

                // GETならCSRFのチェックはしない
                if ("GET" == request.method) return false

                // 特定のURLに該当する場合、CSRFチェックしない
                return !disabledRequestMatcher.matches(request)
            }
        }
        http.csrf().requireCsrfProtectionMatcher(csrfRequestMatcher)
    }
}