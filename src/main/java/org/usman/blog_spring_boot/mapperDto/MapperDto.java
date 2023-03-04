package org.usman.blog_spring_boot.mapperDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CatDto;
import org.usman.blog_spring_boot.utility.Blog;
import org.usman.blog_spring_boot.utility.Cat;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MapperDto{

    MapperDto INSTANCE= Mappers.getMapper(MapperDto.class);


    BlogGeneralDto entityToDto(Blog blog);
    List<BlogGeneralDto> entityToDto(List<Blog> blog);





    CatDto entityToCatDto(Cat cat);
    List<CatDto> entityCatToDto(List<Cat> cats);

    Cat dtoToCatEntity(CatDto dto);
    List<Cat> dtoToCatEntity(List<CatDto> dto);


    Blog dtoToEntity(BlogGeneralDto dto);
    List<Blog> dtoToEntity(List<BlogGeneralDto> dto);


//    2. blogDto

    BlogDto entityToBlogDto(Blog blog);
    List<BlogDto> entityToBlogDto(List<Blog> blog);










}
