package org.usman.blog_spring_boot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CategoryDto;
import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.model.Cat;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MapperDto{

    MapperDto INSTANCE= Mappers.getMapper(MapperDto.class);


    BlogGeneralDto entityToDto(Blog blog);
    List<BlogGeneralDto> entityToDto(List<Blog> blog);





    CategoryDto entityToCatDto(Cat cat);
    List<CategoryDto> entityCatToDto(List<Cat> cats);

    Cat dtoToCatEntity(CategoryDto dto);
    List<Cat> dtoToCatEntity(List<CategoryDto> dto);


    Blog dtoToEntity(BlogGeneralDto dto);
    List<Blog> dtoToEntity(List<BlogGeneralDto> dto);


//    2. blogDto

    BlogDto entityToBlogDto(Blog blog);
    List<BlogDto> entityToBlogDto(List<Blog> blog);










}
