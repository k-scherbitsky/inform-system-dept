package com.scherbitsky.informsystemdept.controller.admin

import com.scherbitsky.informsystemdept.dto.DisciplineDTO
import com.scherbitsky.informsystemdept.dto.SubjectDTO
import com.scherbitsky.informsystemdept.dto.UserDTO
import com.scherbitsky.informsystemdept.model.enums.PositionType
import com.scherbitsky.informsystemdept.model.enums.UserRole
import com.scherbitsky.informsystemdept.repository.DisciplineRepository
import com.scherbitsky.informsystemdept.repository.SubjectRepository
import com.scherbitsky.informsystemdept.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin/add")
class AdminAddController @Autowired constructor(private val userRepository: UserRepository,
                                               private val subjectRepository: SubjectRepository,
                                               private val disciplineRepository: DisciplineRepository) {

    @GetMapping("/subject")
    fun addSubject(model: Model): String {
        val subjectList: List<SubjectDTO> = subjectRepository.findAll().map { SubjectDTO.toDto(it) }

        model.addAttribute("allSubjects", subjectList)
        model.addAttribute("subjectDto", SubjectDTO())

        return "admin/add/subject"
    }

    @PostMapping("/subject")
    fun addSubject(@ModelAttribute subjectDTO: SubjectDTO): String {
        subjectRepository.save(SubjectDTO.fromDto(subjectDTO))
        return "redirect:/admin"
    }

    @GetMapping("/teacher")
    fun addTeacher(model: Model): String {

        model.addAttribute("userDto", UserDTO())
        model.addAttribute("positionType", PositionType.values())
        return "admin/add/teacher"
    }

    @PostMapping("/teacher")
    fun addTeacher(@ModelAttribute userDto: UserDTO): String {
        userDto.userRole = UserRole.USER
        userRepository.save(UserDTO.fromDto(userDto))

        return "admin/home"
    }

    @GetMapping("/discipline")
    fun addDiscipline(model: Model): String {

        val userDtos: List<UserDTO> = userRepository.findAll().map { UserDTO.toDto(it) }
        val subjectList: List<SubjectDTO> = subjectRepository.findAll().map { SubjectDTO.toDto(it) }

        model.addAttribute("userDtos", userDtos)
        model.addAttribute("subjectList", subjectList)
        model.addAttribute("disciplineDto", DisciplineDTO())

        return "admin/add/discipline"
    }

    @PostMapping("/discipline")
    fun addDiscipline(@ModelAttribute disciplineDto: DisciplineDTO): String {

        val subjectEntity = disciplineDto.subjectId?.let { subjectRepository.findById(it).get() }
        val userEntity = disciplineDto.userId?.let { userRepository.findById(it).get() }

        val disciplineEntity = DisciplineDTO.fromDto(disciplineDto)
        disciplineEntity.user = userEntity
        disciplineEntity.subject = subjectEntity

        disciplineRepository.save(disciplineEntity)

        return "redirect:/admin"
    }

}