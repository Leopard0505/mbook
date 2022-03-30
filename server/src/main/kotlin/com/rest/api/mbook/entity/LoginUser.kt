package com.rest.api.mbook.entity

import lombok.Data
import org.jetbrains.annotations.NotNull
import org.springframework.data.annotation.Id

@Data
data class LoginUser(
    @get: NotNull
    val username: String = "",

    @get: NotNull
    val password: String = ""
) {

    /**
     * 入力チェック
     */
    fun validate() {
        if (!matches(username, Regex("^[A-Z0-9]{7}$"))) {
            throw Exception(String.format("username is invalid. [%s]", username))
        }
        if (!matches(password, Regex("^[a-z0-9]{7}$"))) {
            throw Exception("password is invalid.")
        }
        if (username != password.uppercase()) {
            throw Exception("username or password is invalid.")
        }
    }

    /**
     * 値の正規表現チェック
     * @param target: String
     * @param regex: Regex
     * @return 正規表現が正しいならtrue
     */
    private fun matches(target: String, regex: Regex): Boolean {
        return target.matches(regex)
    }
}
