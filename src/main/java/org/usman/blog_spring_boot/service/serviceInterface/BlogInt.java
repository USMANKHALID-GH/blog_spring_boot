package org.usman.blog_spring_boot.service.serviceInterface;

import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.utility.Blog;

import java.util.List;
import java.util.Optional;

public interface BlogInt {
    BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto, Long id);

    List<BlogGeneralDto> showAll();

    BlogGeneralDto blogFindById(Long id);


    List<BlogDto> findAllBlod();

    String updateBlog(Long id, BlogGeneralDto blogGeneralDto);

    String deleteBlog(Long id);

    List<BlogGeneralDto> searchInCentent(String string);

    List<BlogGeneralDto> searchInTitle(String string);

    List<BlogGeneralDto> searchAll(String string ,String string1);

    List<BlogGeneralDto> findByCat(int integer);
}
