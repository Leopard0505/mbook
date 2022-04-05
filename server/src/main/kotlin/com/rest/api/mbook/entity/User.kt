package com.rest.api.mbook.entity

import com.rest.api.mbook.enum.Role
import lombok.Data
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id

/**
 * ユーザー情報 Data
 */
@Data
data class User(
    @Id
    val user_id: Long = 0,

    @get: NotNull
    val user_name: String = "",

    @get: NotNull
    val role_id: Int = 3,

    @get: NotNull
    var token: String? = ""
) {
    fun getRole(): Role {
        return Role.getRole(role_id)
    }
}
