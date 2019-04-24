package com.scherbitsky.informsystemdept.repository

import com.scherbitsky.informsystemdept.model.DisciplineEntity
import org.springframework.data.jpa.repository.JpaRepository

interface DisciplineRepository: JpaRepository<DisciplineEntity, Int> {

}