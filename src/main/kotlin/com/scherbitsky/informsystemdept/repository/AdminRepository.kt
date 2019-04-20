package com.scherbitsky.informsystemdept.repository

import com.scherbitsky.informsystemdept.model.AdminEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AdminRepository: JpaRepository<AdminEntity, Int> {
    fun findByUserName(email: String?): AdminEntity?

}