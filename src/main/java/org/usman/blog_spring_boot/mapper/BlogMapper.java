package org.usman.blog_spring_boot.mapper;

import org.mapstruct.Mapper;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.model.Blog;

@Mapper(componentModel = "spring")
public interface BlogMapper extends  EntityMapper<BlogDto , Blog>{

    BlogDto toDto(Blog blog);
    Blog  toEntity(BlogDto blogDto);
}
