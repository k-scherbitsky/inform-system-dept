package com.scherbitsky.informsystemdept.dto

import com.scherbitsky.informsystemdept.model.DisciplineEntity
import com.scherbitsky.informsystemdept.model.SubjectEntity
import com.scherbitsky.informsystemdept.model.UserEntity

class DisciplineDTO {

    var id: Int? = null
    var userId: Int? = null
    var subjectId: Int? = null
    var lectureHours: Double? = null
    var practiceHours: Double? = null
    var labsHours: Double? = null
    var testHours: Double? = null
    var courseHours: Double? = null
    var examHours: Double? = null
    var diplomaHours: Double? = null

    companion object {
        fun toDto(entity: DisciplineEntity): DisciplineDTO {
            val dto = DisciplineDTO()

            dto.id = entity.id
            dto.userId = entity.user?.id
            dto.subjectId = entity.subject?.id
            dto.lectureHours = entity.lectureHours
            dto.practiceHours = entity.practiceHours
            dto.labsHours = entity.labsHours
            dto.testHours = entity.testHours
            dto.courseHours = entity.courseHours
            dto.examHours = entity.examHours
            dto.diplomaHours = entity.diplomaHours

            return dto
        }

        fun fromDto(dto: DisciplineDTO): DisciplineEntity {
            val entity = DisciplineEntity()

            entity.id = dto.id
            entity.user?.id = dto.userId
            entity.subject?.id = dto.subjectId
            entity.lectureHours = dto.lectureHours
            entity.practiceHours = dto.practiceHours
            entity.labsHours = dto.labsHours
            entity.testHours = dto.testHours
            entity.courseHours = dto.courseHours
            entity.examHours = dto.examHours
            entity.diplomaHours = dto.diplomaHours
            return entity
        }
    }
}