package com.rest.api.mbook.entity

import lombok.Data
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id

/**
 * 本情報 Data
 */
@Data
data class BookInfo(
    @Id
    var book_info_id: Long = 0,

    @get: NotNull
    var book_id: Int = 0,

    @get: NotNull
    val title: String = "",

    @get: NotNull
    val release_date: String = "",

    @get: NotNull
    val special_edition: Boolean = false,

    var created_user: String = "",

    var updated_user: String = ""
)
