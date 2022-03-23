package com.rest.api.mbook.enum

enum class Role(val id: Int) {
    SYSTEM_ADMIN(1),
    ADMIN(2),
    USER(3);

    companion object {
        fun getRole(id: Int): Role {
            return values().filter {
                it.id == id
            }.first()
        }
    }
}