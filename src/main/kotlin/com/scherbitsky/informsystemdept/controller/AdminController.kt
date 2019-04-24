package com.scherbitsky.informsystemdept.controller

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


}