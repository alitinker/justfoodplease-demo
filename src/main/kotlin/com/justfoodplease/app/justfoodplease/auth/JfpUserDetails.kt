package com.justfoodplease.app.justfoodplease.auth

import com.justfoodplease.app.justfoodplease.entities.JfpUser
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors


class JfpUserDetails(
    private val jfpUser : JfpUser
) : UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return jfpUser.roles.stream().map {
            //log.debug("Granting Authority to user with role: ${it.jfpRole}")
            SimpleGrantedAuthority(it.jfpRole.jfpRoleAuthority.toString())
        }.collect(Collectors.toList())
    }

    override fun getPassword(): String {
        return jfpUser.jfpPassword
    }

    override fun getUsername(): String = jfpUser.jfpUsername

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        // TODO: enhance user properties
        return true
    }

    override fun isEnabled(): Boolean {
        return jfpUser.active
    }

}
