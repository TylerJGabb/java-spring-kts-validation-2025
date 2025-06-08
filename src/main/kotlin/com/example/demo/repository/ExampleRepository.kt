package com.example.demo.repository

import com.example.demo.entity.ExampleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ExampleRepository : JpaRepository<ExampleEntity, Long>
