package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/util")
class UtilController {

    @GetMapping("/boom")
    fun boom(): String {
        throw RuntimeException("This is a test exception to demonstrate error handling")
    }

}