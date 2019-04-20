package com.scherbitsky.informsystemdept.security

import com.scherbitsky.informsystemdept.model.AdminDetailsImpl
import com.scherbitsky.informsystemdept.model.AdminEntity
import com.scherbitsky.informsystemdept.repository.AdminRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService {

    @Autowired
    private lateinit var adminRepository: AdminRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder


    fun create(user: AdminEntity): AdminEntity {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return adminRepository.save(user)
    }

    private fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as AdminDetailsImpl
        return user.username
    }

}