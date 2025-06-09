package com.example.demo.service

import com.example.demo.dto.ExampleDto
import com.example.demo.mapper.ExampleMapper
import com.example.demo.repository.ExampleRepository
import jakarta.persistence.EntityNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ExampleService(private val repository: ExampleRepository) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun create(dto: ExampleDto): ExampleDto {
        logger.info("Creating new example")
        val entity = ExampleMapper.toEntity(dto)
        return ExampleMapper.toDto(repository.save(entity))
    }

    @Transactional
    fun update(id: Long, dto: ExampleDto): ExampleDto {
        logger.info("Updating example {}", id)
        val entity = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity $id not found") }
        ExampleMapper.updateEntityFromDto(entity, dto)
        return ExampleMapper.toDto(repository.save(entity))
    }

    fun get(id: Long): ExampleDto {
        logger.info("Fetching example {}", id)
        val entity = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity $id not found") }
        return ExampleMapper.toDto(entity)
    }

    fun getAll(): List<ExampleDto> {
        logger.info("Fetching all examples")
        return repository.findAll().map { ExampleMapper.toDto(it) }
    }

    @Transactional
    fun delete(id: Long) {
        logger.info("Deleting example {}", id)
        val entity = repository.findById(id)
            .orElseThrow { EntityNotFoundException("Entity $id not found") }
        repository.delete(entity)
    }
}
