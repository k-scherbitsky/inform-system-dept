package com.scherbitsky.informsystemdept.dto

import com.scherbitsky.informsystemdept.model.AdminEntity
import com.scherbitsky.informsystemdept.model.enums.UserRole

class AdminDTO {
    var id: Int? = null
    var userName: String? = null
    var password: String? = null
    var userRole: UserRole? = null

    companion object {
        fun toDto(adminEntity: AdminEntity): AdminDTO {
            val dto = AdminDTO()

            dto.id = adminEntity.id
            dto.userName = adminEntity.userName
            dto.password = adminEntity.password
            dto.userRole = adminEntity.userRole

            return dto
        }

        fun fromDto(adminDto: AdminDTO): AdminEntity{
            val entity = AdminEntity()

            entity.id = adminDto.id
            entity.userName = adminDto.userName
            entity.password = adminDto.password
            entity.userRole = adminDto.userRole

            return entity
        }
    }
}