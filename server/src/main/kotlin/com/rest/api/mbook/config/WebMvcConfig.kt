package com.rest.api.mbook.config

import com.rest.api.mbook.interceptor.AuthorizationInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * WebMvcConfig 共通処理
 */
@Configuration
class WebMvcConfig: WebMvcConfigurer {

    /**
     * 認証 Interceptor
     */
    @Bean
    fun authorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    /**
     * 認証処理追加
     */
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(authorizationInterceptor())
            .addPathPatterns("/api/v1/**")
    }
}