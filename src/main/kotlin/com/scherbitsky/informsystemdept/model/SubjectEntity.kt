package com.scherbitsky.informsystemdept.model

import javax.persistence.*

@Entity
@Table(name = "subjects", schema = "public")
class SubjectEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(name = "subject_name")
    var subjectName: String? = null

    @Column(name = "speciality")
    var speciality: String? = null
}