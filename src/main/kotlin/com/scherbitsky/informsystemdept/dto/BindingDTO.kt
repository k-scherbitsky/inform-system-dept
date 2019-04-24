package com.scherbitsky.informsystemdept.dto

import com.scherbitsky.informsystemdept.model.BindingEntity

class BindingDTO {

    var id: Int? = null
    var userId: Int? = null
    var subjectId: Int? = null
    var teacherFullName: String? = null
    var subjectName: String? = null
    var lectureHours: Int? = null
    var practiceHours: Int? = null
    var labsHours: Int? = null
    var testHours: Int? = null
    var courseHours: Int? = null
    var examHours: Int? = null
    var diplomaHours: Int? = null

    companion object {
        fun toDto(entity: BindingEntity): BindingDTO {
            val dto = BindingDTO()

            dto.id = entity.id
            dto.userId = entity.user?.id
            dto.subjectId = entity.subject?.id
            dto.teacherFullName = "${entity.user?.surname} ${entity.user?.name} ${entity.user?.middleName}"
            dto.subjectName = "${entity.subject?.subjectName}/${entity.subject?.speciality}"
            dto.lectureHours = entity.lectureHours?.toInt()
            dto.practiceHours = entity.practiceHours?.toInt()
            dto.labsHours = entity.labsHours?.toInt()
            dto.testHours = entity.testHours?.toInt()
            dto.courseHours = entity.courseHours?.toInt()
            dto.examHours = entity.examHours?.toInt()
            dto.diplomaHours = entity.diplomaHours?.toInt()

            return dto
        }

        fun fromDto(dto: BindingDTO): BindingEntity {
            val entity = BindingEntity()

            entity.id = dto.id
            entity.user?.id = dto.userId
            entity.subject?.id = dto.subjectId
            entity.lectureHours = dto.lectureHours?.toDouble()
            entity.practiceHours = dto.practiceHours?.toDouble()
            entity.labsHours = dto.labsHours?.toDouble()
            entity.testHours = dto.testHours?.toDouble()
            entity.courseHours = dto.courseHours?.toDouble()
            entity.examHours = dto.examHours?.toDouble()
            entity.diplomaHours = dto.diplomaHours?.toDouble()
            return entity
        }
    }
}