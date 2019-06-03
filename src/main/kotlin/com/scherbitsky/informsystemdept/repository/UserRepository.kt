package com.scherbitsky.informsystemdept.repository

import com.scherbitsky.informsystemdept.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Int> {

    fun findUserEntityBySurnameAndNameAndMiddleName(surname: String, name: String, middleName: String): UserEntity?

    fun findUserEntitiesBySurnameAndName(surname: String, name: String): UserEntity?

}