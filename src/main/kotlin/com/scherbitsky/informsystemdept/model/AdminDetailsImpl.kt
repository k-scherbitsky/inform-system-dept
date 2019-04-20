package com.scherbitsky.informsystemdept.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AdminDetailsImpl(private val adminEntity: AdminEntity?) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String? = adminEntity?.userName

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String? = adminEntity?.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}