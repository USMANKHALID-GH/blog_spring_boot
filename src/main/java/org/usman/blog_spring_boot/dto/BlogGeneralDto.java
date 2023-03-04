package org.usman.blog_spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.usman.blog_spring_boot.utility.Cat;

import java.util.Date;
@Data
public class BlogGeneralDto {
    @NotNull(message="Title must be provided")
    private  String title;
    @NotNull(message="content must be provided")
    private String content;
    private String image;







}
