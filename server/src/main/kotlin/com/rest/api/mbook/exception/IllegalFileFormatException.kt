package com.rest.api.mbook.exception

/**
 * ファイルフォーマットが一致しない場合にthrowされるException
 */
class IllegalFileFormatException(message: String?): RuntimeException(message)
