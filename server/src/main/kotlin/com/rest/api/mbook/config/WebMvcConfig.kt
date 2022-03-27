package com.rest.api.mbook.config

import com.rest.api.mbook.interceptor.AuthorizationInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
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

    /**
     * CORSの設定
     */
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST")
            .allowedHeaders("Authorization", "Content-Type")
    }
}