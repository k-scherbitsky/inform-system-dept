package com.scherbitsky.informsystemdept.repository

import com.scherbitsky.informsystemdept.model.BindingEntity
import org.springframework.data.jpa.repository.JpaRepository

interface BindingRepository: JpaRepository<BindingEntity, Int> {

}