
package com.example.demo.service;

import com.example.demo.dto.ExampleDto;
import com.example.demo.entity.ExampleEntity;
import com.example.demo.mapper.ExampleMapper;
import com.example.demo.repository.ExampleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {

    private final ExampleRepository repository;

    public ExampleService(ExampleRepository repository) {
        this.repository = repository;
    }

    public ExampleDto create(ExampleDto dto) {
        ExampleEntity entity = ExampleMapper.toEntity(dto);
        return ExampleMapper.toDto(repository.save(entity));
    }

    public ExampleDto update(Long id, ExampleDto dto) {
        ExampleEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity " + id + " not found"));
        ExampleMapper.updateEntityFromDto(entity, dto);
        return ExampleMapper.toDto(repository.save(entity));
    }

    public ExampleDto get(Long id) {
        ExampleEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity " + id + " not found"));
        return ExampleMapper.toDto(entity);
    }

    public List<ExampleDto> getAll() {
        return repository.findAll().stream()
            .map(ExampleMapper::toDto)
            .toList();
    }

    public void delete(Long id) {
        ExampleEntity entity = repository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Entity " + id + " not found"));
        repository.delete(entity);
    }
}
