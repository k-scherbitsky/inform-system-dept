package com.scherbitsky.informsystemdept.controller

import com.scherbitsky.informsystemdept.dto.AdminDTO
import com.scherbitsky.informsystemdept.model.enums.UserRole
import com.scherbitsky.informsystemdept.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class MainController @Autowired constructor(private val adminService: AdminService) {

    @RequestMapping("/")
    fun index(mode: Model): String {
        return "index"
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun signUp(@RequestBody admin: AdminDTO): AdminDTO {
        admin.userRole = UserRole.ADMIN
        return adminService.create(admin)
    }

    @GetMapping("/login")
    fun login(): String {
        return "login"
    }
}