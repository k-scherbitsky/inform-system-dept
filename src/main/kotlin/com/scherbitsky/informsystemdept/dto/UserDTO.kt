package com.scherbitsky.informsystemdept.dto

import com.scherbitsky.informsystemdept.model.UserEntity
import com.scherbitsky.informsystemdept.model.enums.PositionType
import com.scherbitsky.informsystemdept.model.enums.UserRole

class UserDTO {

    var id: Int? = null
    var name: String? = null
    var surname: String? = null
    var middleName: String? = null
    var position: PositionType? = null
    var userRole: UserRole? = null

    companion object {
        fun toDto(userEntity: UserEntity): UserDTO {
            val dto = UserDTO()

            dto.id = userEntity.id
            dto.name = userEntity.name
            dto.surname = userEntity.surname
            dto.middleName = userEntity.middleName
            dto.position = userEntity.position
            dto.userRole = userEntity.userRole

            return dto
        }

        fun fromDto(userDto: UserDTO): UserEntity {
            val entity = UserEntity()

            entity.id = userDto.id
            entity.name = userDto.name
            entity.surname = userDto.surname
            entity.middleName = userDto.middleName
            entity.position = userDto.position
            entity.userRole = userDto.userRole

            return entity
        }
    }
}