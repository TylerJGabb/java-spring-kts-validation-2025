
package com.example.demo.dto;

import com.example.demo.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ExampleDto {

    private Long id;

    @NotBlank(groups = OnCreate.class, message = "Name is required for creation")
    private String name;

    private String description;
}
