package com.example.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "examples")
class ExampleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String = ""

    @Column
    var description: String? = null
}
