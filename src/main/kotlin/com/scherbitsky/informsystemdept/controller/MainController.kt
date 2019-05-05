package com.scherbitsky.informsystemdept.controller

import com.scherbitsky.informsystemdept.dto.AdminDTO
import com.scherbitsky.informsystemdept.dto.BindingDTO
import com.scherbitsky.informsystemdept.dto.UserDTO
import com.scherbitsky.informsystemdept.model.enums.UserRole
import com.scherbitsky.informsystemdept.repository.BindingRepository
import com.scherbitsky.informsystemdept.repository.UserRepository
import com.scherbitsky.informsystemdept.service.AdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
class MainController @Autowired constructor(private val adminService: AdminService,
                                            private val userRepository: UserRepository,
                                            private val bindingRepository: BindingRepository) {

    @RequestMapping("/")
    fun index(mode: Model): String {
        return "index"
    }

    @GetMapping("/load")
    fun loadPage(model: Model): String {
        model.addAttribute("userDto", UserDTO())
        return "load/home"
    }

    @PostMapping("/load/result")
    fun renderResultLoadPage(@ModelAttribute userDto: UserDTO, model: Model): String {

        val userEntity = userRepository
                .findUserEntityBySurnameAndNameAndMiddleName(userDto.surname!!, userDto.name!!, userDto.middleName!!)

        val bindingList = bindingRepository.findAllByUser(userEntity).map { BindingDTO.toDto(it) }

        model.addAttribute("bindingList", bindingList)
        return "load/result"
    }

    @PostMapping("/signup")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun signUpPage(@RequestBody admin: AdminDTO): AdminDTO {
        admin.userRole = UserRole.ROLE_ADMIN
        return adminService.create(admin)
    }

    @GetMapping("/login")
    fun loginPage(): String {
        return "login"
    }
}