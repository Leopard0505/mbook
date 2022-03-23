package com.rest.api.mbook.exception

/**
 * 操作しようとしたリソースが存在しない場合にthrowされるException
 */
class NotFoundException(message: String?) : RuntimeException(message)
