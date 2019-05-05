package com.scherbitsky.informsystemdept.controller.admin

import com.scherbitsky.informsystemdept.dto.BindingDTO
import com.scherbitsky.informsystemdept.dto.SubjectDTO
import com.scherbitsky.informsystemdept.dto.UserDTO
import com.scherbitsky.informsystemdept.model.enums.PositionType
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
    fun home(model: Model): String {
        return "admin/home"
    }

    @GetMapping("/binding")
    fun bindingTablePage(model: Model): String {
        val bindingDtoList: List<BindingDTO> = bindingRepository.findAll().map { BindingDTO.toDto(it) }
        val userDtos: List<UserDTO> = userRepository.findAll().map { UserDTO.toDto(it) }
        val subjectList: List<SubjectDTO> = subjectRepository.findAll().map { SubjectDTO.toDto(it) }

        model.addAttribute("userDtos", userDtos)
        model.addAttribute("subjectList", subjectList)
        model.addAttribute("bindingDtoList", bindingDtoList)
        model.addAttribute("bindingDto", BindingDTO())

        return "admin/manage/binding"
    }

    @GetMapping("/teacher")
    fun teacherTablePage(model: Model): String {
        val userDtoList: List<UserDTO> = userRepository.findAll().map { UserDTO.toDto(it) }
        model.addAttribute("userDto", UserDTO())
        model.addAttribute("positionType", PositionType.values())
        model.addAttribute("userDtoList", userDtoList)
        return "admin/manage/teacher"
    }

    @GetMapping("/subject")
    fun subjectTablePage(model: Model): String {
        val subjectDtoList: List<SubjectDTO> = subjectRepository.findAll().map { SubjectDTO.toDto(it) }
        model.addAttribute("subjectDto", SubjectDTO())
        model.addAttribute("subjectDtoList", subjectDtoList)
        return "admin/manage/subject"
    }


}