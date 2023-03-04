package org.usman.blog_spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
@Data
public class BlogGeneralDto {
    @NotNull(message="Title must be provided")
    private  String title;
    @NotNull(message="content must be provided")
    private String content;
    private String image;
    @NotNull(message="date created must be provided")
    private Date dateCreated;
}
