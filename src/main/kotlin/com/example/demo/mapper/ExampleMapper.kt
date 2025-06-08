package com.example.demo.mapper

import com.example.demo.dto.ExampleDto
import com.example.demo.entity.ExampleEntity

object ExampleMapper {
    fun toEntity(dto: ExampleDto): ExampleEntity {
        val entity = ExampleEntity()
        dto.name?.let { entity.name = it }
        entity.description = dto.description
        return entity
    }

    fun toDto(entity: ExampleEntity): ExampleDto {
        return ExampleDto(
            id = entity.id,
            name = entity.name,
            description = entity.description
        )
    }

    fun updateEntityFromDto(entity: ExampleEntity, dto: ExampleDto) {
        dto.name?.takeIf { it.isNotBlank() }?.let { entity.name = it }
        dto.description?.let { entity.description = it }
    }
}
