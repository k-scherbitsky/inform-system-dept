package com.scherbitsky.informsystemdept.dto

import com.scherbitsky.informsystemdept.model.SubjectEntity

class SubjectDTO {

    var id: Int? = null
    var subjectName: String? = null
    var speciality: String? = null

    companion object {
        fun toDto(entity: SubjectEntity): SubjectDTO {
            val dto = SubjectDTO()

            dto.id = entity.id
            dto.subjectName = entity.subjectName
            dto.speciality = entity.speciality

            return dto
        }

        fun fromDto(dto: SubjectDTO): SubjectEntity {
            val entity = SubjectEntity()

            entity.id = dto.id
            entity.speciality = dto.speciality
            entity.subjectName = dto.subjectName

            return entity
        }
    }
}