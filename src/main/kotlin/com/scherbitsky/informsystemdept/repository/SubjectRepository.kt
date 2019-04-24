package com.scherbitsky.informsystemdept.repository

import com.scherbitsky.informsystemdept.model.SubjectEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SubjectRepository: JpaRepository<SubjectEntity, Int> {
}