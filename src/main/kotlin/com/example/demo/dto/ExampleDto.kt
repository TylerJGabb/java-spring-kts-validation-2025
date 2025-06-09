package com.example.demo.dto

import com.example.demo.validation.OnCreate
import com.example.demo.validation.OnUpdate
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ExampleDto(
    var id: Long? = null,

    @field:NotBlank(groups = [OnCreate::class], message = "Name is required for creation")
    @field:Schema(
        example = "Sample Name"
    )
    @field:Pattern(
        regexp = "^[a-zA-Z0-9 ]{3,64}\$",
        groups = [OnCreate::class, OnUpdate::class],
        message = "Name can only contain letters, numbers, and spaces. It must be between 3 and 64 characters long."
    )
    var name: String? = null,

    var description: String? = null
)
