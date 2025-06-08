package com.example.demo.service

import com.example.demo.dto.ExampleDto
import com.example.demo.entity.ExampleEntity
import com.example.demo.mapper.ExampleMapper
import com.example.demo.repository.ExampleRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class ExampleService(private val repository: ExampleRepository) {

    fun create(dto: ExampleDto): ExampleDto {
        val entity = ExampleMapper.toEntity(dto)
        return ExampleMapper.toDto(repository.save(entity))
    }

    fun update(id: Long, dto: ExampleDto): ExampleDto {
        val entity = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity $id not found") }
        ExampleMapper.updateEntityFromDto(entity, dto)
        return ExampleMapper.toDto(repository.save(entity))
    }

    fun get(id: Long): ExampleDto {
        val entity = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity $id not found") }
        return ExampleMapper.toDto(entity)
    }

    fun getAll(): List<ExampleDto> =
        repository.findAll().map { ExampleMapper.toDto(it) }

    fun delete(id: Long) {
        val entity = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity $id not found") }
        repository.delete(entity)
    }
}
