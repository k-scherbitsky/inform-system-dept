package com.scherbitsky.informsystemdept.model

import javax.persistence.*

@Entity
@Table(name = "discipline", schema = "public")
class DisciplineEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: UserEntity? = null

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
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