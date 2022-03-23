package com.rest.api.mbook.entity

import lombok.Data
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id
import java.util.Date

/**
 * 本情報 Data
 */
@Data
data class Book (
    @Id
    var book_id: Long = 0,

    @get: NotNull
    val title: String = "",

    @get: NotNull
    val release_date: Date = Date(),

    @get: NotNull
    val original_author: String = "",

    @get: NotNull
    val drawer: String = "",

    @get: NotNull
    val publisher: String = "",

    var created_user: String = "",

    var updated_user: String = ""
)
