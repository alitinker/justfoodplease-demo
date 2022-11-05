package com.justfoodplease.app.justfoodplease.entities

import org.springframework.security.core.GrantedAuthority


enum class JfpRoleAuthority: GrantedAuthority {
    ROLE_ADMIN {
        override fun getAuthority(): String = name
    },
    ROLE_CLIENT {
        override fun getAuthority(): String = name
    };
}