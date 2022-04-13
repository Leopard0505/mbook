package com.rest.api.mbook.entity

import lombok.Data

/**
 * エラーレスポンス用
 */
@Data
data class ErrorResponse (
    var errorCode: String? = null,
    var message: String? = null
)