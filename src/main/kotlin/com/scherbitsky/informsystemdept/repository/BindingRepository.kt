package com.scherbitsky.informsystemdept.repository

import com.scherbitsky.informsystemdept.model.BindingEntity
import com.scherbitsky.informsystemdept.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BindingRepository : JpaRepository<BindingEntity, Int> {
    fun findBindingEntityByUser(user: UserEntity): BindingEntity

    fun findAllByUser(user: UserEntity): List<BindingEntity>

}