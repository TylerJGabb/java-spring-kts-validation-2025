package com.example.demo.dto

import com.example.demo.validation.OnCreate
import jakarta.validation.constraints.NotBlank

data class ExampleDto(
    var id: Long? = null,
    @field:NotBlank(groups = [OnCreate::class], message = "Name is required for creation")
    var name: String? = null,
    var description: String? = null
)
