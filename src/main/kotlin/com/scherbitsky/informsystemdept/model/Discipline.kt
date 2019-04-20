package com.scherbitsky.informsystemdept.model

import javax.persistence.*

@Entity
@Table(name = "discipline", schema = "public")
class Discipline {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(name = "user_id")
    var user: UserEntity? = null

    @Column(name = "subject_id")
    var subject: SubjectEntity? = null

    @Column(name = "lecture_hours")
    var lectureHours: Double? = null

    @Column(name = "practice_hours")
    var practiceHours: Double? = null

    @Column(name = "labs_hours")
    var labsHours: Double? = null

    @Column(name = "test_hours")
    var testHours: Double? = null

    @Column(name = "course_hours")
    var courseHours: Double? = null

    @Column(name = "exam_hours")
    var examHours: Double? = null

    @Column(name = "diploma_hours")
    var diplomaHours: Double? = null

}