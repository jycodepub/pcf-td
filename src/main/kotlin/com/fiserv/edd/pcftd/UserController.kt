package com.fiserv.edd.pcftd

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class UserController(private val repository: UserRepository) {

    @GetMapping("/users/form")
    fun showForm(@RequestParam show: String, model: Model): String {
        model.addAttribute("user", User())
        when (show) {
            "add" -> return "addUser"
            "search" -> return "searchUsers"
        }
        throw IllegalArgumentException("Unknown page name: $show")
    }

    @PostMapping("/users/add")
    fun addUser(@ModelAttribute user: User, model: Model): String {
        repository.save(user)
        model.addAttribute("response", "Saved user: ${user.username}")
        return "addUser"
    }

    @PostMapping("/users/search")
    fun searchUsers(@ModelAttribute user: User, model: Model): String {
        if (user.username == null || user.username!!.trim().length == 0) {
            model.addAttribute("hits", repository.findAll().toList())
        } else {
            model.addAttribute("hits", repository.findByUsername(user.username!!))
        }
        return "searchUsers"
    }
}

fun <T> Iterator<T>.toList(): List<T> =
    ArrayList<T>().apply {
        while (hasNext())
            this += next()
    }