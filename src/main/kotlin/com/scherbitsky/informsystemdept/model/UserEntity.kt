package com.scherbitsky.informsystemdept.model

import javax.persistence.*

@Entity
@Table(name = "users", schema = "public")
class UserEntity{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null

    @Column(name = "name")
    var name: String? = null

    @Column(name = "surname")
    var surname: String? = null

    @Column(name = "middle_name")
    var middleName: String? = null

    @Column(name = "position")
    var position: PositionType? = null

    @Column(name = "user_role")
    var userRole: UserRole? = null



}