package com.example.demo.controller

import com.example.demo.dto.ExampleDto
import com.example.demo.service.ExampleService
import com.example.demo.validation.OnCreate
import com.example.demo.validation.OnUpdate
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/examples")
class ExampleController(private val service: ExampleService) {

    @PostMapping
    fun create(@RequestBody @Validated(OnCreate::class) dto: ExampleDto): ResponseEntity<ExampleDto> =
        ResponseEntity.status(201).body(service.create(dto))

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Validated(OnUpdate::class) dto: ExampleDto): ResponseEntity<ExampleDto> =
        ResponseEntity.ok(service.update(id, dto))

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<ExampleDto> =
        ResponseEntity.ok(service.get(id))

    @GetMapping
    fun getAll(): ResponseEntity<List<ExampleDto>> =
        ResponseEntity.ok(service.getAll())

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
