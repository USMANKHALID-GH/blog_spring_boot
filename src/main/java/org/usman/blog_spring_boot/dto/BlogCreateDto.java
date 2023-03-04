package org.usman.blog_spring_boot.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.usman.blog_spring_boot.utility.Blog;

import java.util.Date;
import java.util.List;

@Data
public class BlogCreateDto {

    private  Long id;
    @NotNull(message="Title must be provided")
    private  String title;
    @NotNull(message="content must be provided")
    private String content;
    private String image;
    @NotNull(message="date created must be provided")
    private Date dateCreated;
    private List<Blog> blog;
}
