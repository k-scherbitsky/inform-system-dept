package com.scherbitsky.informsystemdept.model

import com.scherbitsky.informsystemdept.model.enums.UserRole
import org.hibernate.annotations.Type
import javax.persistence.*

@Entity
@Table(name = "admins", schema = "public")
class AdminEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "user_role")
    var userRole: UserRole? = null

    @Column(name = "username")
    var userName: String? = null

    @Column(name = "password")
    var password: String? = null

}