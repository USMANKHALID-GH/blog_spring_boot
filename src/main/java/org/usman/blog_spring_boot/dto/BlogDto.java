package org.usman.blog_spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.usman.blog_spring_boot.model.Cat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BlogDto extends BaseDto {


    @NotNull(message="Title must be provided")
    private  String title;
    @NotNull(message="content must be provided")
    private String content;
    private String image;
    @NotNull(message="date created must be provided")
    private LocalDateTime createdDate;
    private Cat cat;
}
