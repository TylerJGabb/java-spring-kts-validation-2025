
package com.example.demo.controller;

import com.example.demo.dto.ExampleDto;
import com.example.demo.service.ExampleService;
import com.example.demo.validation.OnCreate;
import com.example.demo.validation.OnUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    private final ExampleService service;

    public ExampleController(ExampleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExampleDto> create(@RequestBody @Validated(OnCreate.class) ExampleDto dto) {
        return ResponseEntity.status(201).body(service.create(dto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ExampleDto> update(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) ExampleDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExampleDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<ExampleDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
