package com.scherbitsky.informsystemdept.controller.admin

import com.scherbitsky.informsystemdept.dto.BindingDTO
import com.scherbitsky.informsystemdept.repository.BindingRepository
import com.scherbitsky.informsystemdept.repository.SubjectRepository
import com.scherbitsky.informsystemdept.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/remove")
class AdminRemoveController @Autowired constructor(private val userRepository: UserRepository,
                                                   private val subjectRepository: SubjectRepository,
                                                   private val bindingRepository: BindingRepository) {

    @DeleteMapping("/teacher/{id}")
    fun removeTeacher(@PathVariable id: Int) = userRepository.deleteById(id)

    @DeleteMapping("/subject/{id}")
    fun removeSubject(@PathVariable id: Int) = subjectRepository.deleteById(id)

    @DeleteMapping("/binding/{id}")
    fun removeBinding(@PathVariable id: Int) = bindingRepository.deleteById(id)

}