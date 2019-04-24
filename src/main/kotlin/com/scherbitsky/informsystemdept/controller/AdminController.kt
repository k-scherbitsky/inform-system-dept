package com.scherbitsky.informsystemdept.controller

import com.scherbitsky.informsystemdept.dto.BindingDTO
import com.scherbitsky.informsystemdept.dto.SubjectDTO
import com.scherbitsky.informsystemdept.dto.UserDTO
import com.scherbitsky.informsystemdept.repository.BindingRepository
import com.scherbitsky.informsystemdept.repository.SubjectRepository
import com.scherbitsky.informsystemdept.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/admin")
class AdminController
@Autowired constructor(private val userRepository: UserRepository,
                       private val subjectRepository: SubjectRepository,
                       private val bindingRepository: BindingRepository) {

    @GetMapping("")
    fun home(model: Model): ModelAndView {
        return ModelAndView("admin/home")
    }

    @GetMapping("/binding")
    fun bindingTable(model: Model): String {
        val bindingDtoList: List<BindingDTO> = bindingRepository.findAll().map { BindingDTO.toDto(it) }
        model.addAttribute("bindingDtoList", bindingDtoList)
        return "admin/manage/binding"
    }

    @GetMapping("/teacher")
    fun teacherTable(model: Model): String {
        val userDtoList: List<UserDTO> = userRepository.findAll().map { UserDTO.toDto(it) }
        model.addAttribute("userDtoList", userDtoList)
        return "admin/manage/teacher"
    }

    @GetMapping("/subject")
    fun subjectTable(model: Model): String {
        val subjectDtoList: List<SubjectDTO> = subjectRepository.findAll().map { SubjectDTO.toDto(it) }
        model.addAttribute("subjectDtoList", subjectDtoList)
        return "admin/manage/subject"
    }


}