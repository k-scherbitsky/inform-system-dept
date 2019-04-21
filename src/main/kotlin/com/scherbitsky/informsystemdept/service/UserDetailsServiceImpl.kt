package com.scherbitsky.informsystemdept.service

import com.scherbitsky.informsystemdept.model.AdminDetailsImpl
import com.scherbitsky.informsystemdept.repository.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AdminDetailsServiceImpl
@Autowired constructor(private val adminRepository: AdminRepository) : UserDetailsService {

    override fun loadUserByUsername(userName: String?): UserDetails {
        val admin = adminRepository.findByUserName(userName)

        return AdminDetailsImpl(admin)
    }
}