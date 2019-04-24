package com.scherbitsky.informsystemdept.model

import com.scherbitsky.informsystemdept.model.enums.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class AdminDetailsImpl(private val adminEntity: AdminEntity?) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {

        val roleNames = UserRole.values()
        val grantList: MutableList<GrantedAuthority> = ArrayList()

        for (role in roleNames){
            val authority: GrantedAuthority = SimpleGrantedAuthority(role.name)
            grantList.add(authority)
        }

        return grantList
    }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String? = adminEntity?.userName

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String? = adminEntity?.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}