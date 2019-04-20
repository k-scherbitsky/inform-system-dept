package com.scherbitsky.informsystemdept.model

import javax.persistence.*

@Entity
@Table(name = "admins", schema = "public")
class AdminEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(name = "user_role")
    var userRole: UserRole? = null

    @Column(name = "username")
    var userName: String? = null

    @Column(name = "user_role")
    var password: String? = null

}