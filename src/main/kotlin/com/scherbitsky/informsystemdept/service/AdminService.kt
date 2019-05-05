package com.scherbitsky.informsystemdept.service

import com.scherbitsky.informsystemdept.dto.AdminDTO
import com.scherbitsky.informsystemdept.security.AdminDetailsImpl
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


    fun create(adminDTO: AdminDTO): AdminDTO {
        adminDTO.password = bCryptPasswordEncoder.encode(adminDTO.password)
        return AdminDTO.toDto(adminRepository.save(AdminDTO.fromDto(adminDTO)))
    }

    private fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as AdminDetailsImpl
        return user.username
    }

}