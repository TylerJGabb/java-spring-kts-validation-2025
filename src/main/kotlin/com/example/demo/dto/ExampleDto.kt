package com.example.demo.dto

import com.example.demo.validation.OnCreate
import com.example.demo.validation.OnUpdate
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ExampleDto(
    var id: Long? = null,
    @field:NotBlank(groups = [OnCreate::class], message = "Name is required for creation")
    @field:Pattern(
        regexp = "^[A-Za-z0-9 ]+$",
        groups = [OnCreate::class, OnUpdate::class],
        message = "Name can only contain letters, numbers, and spaces"
    )
    var name: String? = null,
    var description: String? = null
)
