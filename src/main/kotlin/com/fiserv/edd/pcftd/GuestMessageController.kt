package com.fiserv.edd.pcftd

import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class GuestMessageController(private val repository: GuestMessageRepository,
                             private val outputChannel: GuestMessageOutputChannel) {

    @GetMapping("/messages/form")
    fun showForm(@RequestParam show: String, model: Model): String {
        model.addAttribute("msg", GuestMessage())
        when (show) {
            "add" -> return "addMessage"
            "search" -> return "searchMessages"
        }
        throw IllegalArgumentException("Unknown page name: $show")
    }

    @PostMapping("/messages/add")
    fun addMessage(@ModelAttribute("msg") msg: GuestMessage, model: Model): String {
        outputChannel.outputChannel().send(MessageBuilder.withPayload(msg).build())
        //rabbitTemplate.convertAndSend("input.guest.message", msg)
        model.addAttribute("response", "Thank you ${msg.name}! Your message has been saved.")
        return "addMessage"
    }

    @PostMapping("/messages/search")
    fun searchMessages(@ModelAttribute("msg") msg: GuestMessage, model: Model): String {
        if (msg.name.trim().isEmpty()) {
            model.addAttribute("hits", repository.findAll().toList())
        } else {
            model.addAttribute("hits", repository.findByName(msg.name))
        }
        return "searchMessages"
    }
}

fun <T> Iterator<T>.toList(): List<T> =
    ArrayList<T>().apply {
        while (hasNext())
            this += next()
    }