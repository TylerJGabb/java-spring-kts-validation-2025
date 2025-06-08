
package com.example.demo.mapper;

import com.example.demo.dto.ExampleDto;
import com.example.demo.entity.ExampleEntity;

public class ExampleMapper {

    public static ExampleEntity toEntity(ExampleDto dto) {
        ExampleEntity entity = new ExampleEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static ExampleDto toDto(ExampleEntity entity) {
        ExampleDto dto = new ExampleDto();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setId(entity.getId());
        return dto;
    }

    public static void updateEntityFromDto(ExampleEntity entity, ExampleDto dto) {
        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            entity.setDescription(dto.getDescription());
        }
    }
}
