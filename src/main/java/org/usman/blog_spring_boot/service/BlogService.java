package org.usman.blog_spring_boot.service;

import org.springframework.data.domain.Page;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.model.Blog;

import java.util.List;

public interface BlogService {
    BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto, Long id);

    List<BlogGeneralDto> showAllBlog();

    BlogGeneralDto FindByBlogId(Long id);


    List<Blog> findAllBlog();

    String updateBlog(Long id, BlogGeneralDto blogGeneralDto);

    String deleteBlog(Long id);

    List<BlogGeneralDto> searchInContent(String string);

    List<BlogGeneralDto> searchInTitle(String string);

    List<BlogGeneralDto> searchTitleOrContent(String string , String string1);

    List<BlogGeneralDto> findByCat(int integer);

    List<BlogDto>    findByCategory(int integer);
}
