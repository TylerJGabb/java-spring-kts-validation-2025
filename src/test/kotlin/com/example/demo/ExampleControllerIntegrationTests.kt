package com.example.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ExampleControllerIntegrationTests @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    fun `CRUD flow works with JSON`() {
        val createJson = "{" + "\"name\":\"Foo\",\"description\":\"desc\"}"
        val createResult = mockMvc.perform(
            post("/examples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createJson)
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.id").isNumber)
            .andExpect(jsonPath("$.name").value("Foo"))
            .andExpect(jsonPath("$.description").value("desc"))
            .andReturn()

        val id = objectMapper.readTree(createResult.response.contentAsString)["id"].asLong()

        val updateJson = "{" + "\"name\":\"Bar\"}"
        mockMvc.perform(
            patch("/examples/$id")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateJson)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("Bar"))
            .andExpect(jsonPath("$.description").value("desc"))

        mockMvc.perform(get("/examples/$id"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(id))
            .andExpect(jsonPath("$.name").value("Bar"))
            .andExpect(jsonPath("$.description").value("desc"))

        mockMvc.perform(get("/examples"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$[0].id").value(id))

        mockMvc.perform(delete("/examples/$id"))
            .andExpect(status().isNoContent)
    }

    @Test
    fun `validation and not found return proper status`() {
        val invalidJson = "{}"
        mockMvc.perform(
            post("/examples")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidJson)
        ).andExpect(status().isBadRequest)

        mockMvc.perform(get("/examples/999999"))
            .andExpect(status().isNotFound)
    }
}
