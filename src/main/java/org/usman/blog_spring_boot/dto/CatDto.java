package org.usman.blog_spring_boot.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Valid
@Data
public class CatDto {
    @NotBlank(message="category must not be null1")
    private String category;



}
