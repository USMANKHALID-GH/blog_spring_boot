package org.usman.blog_spring_boot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.model.Blog;

public interface PaginationService {
    void create(Blog toEntity);
    Page<Blog> findAllByCategory(Pageable pageable, int search);
    Blog findBlogBYId(Long integer);
    void deleteBlogBYId(Long integer);
    void updateBlogId(Long integer, BlogDto blogDto);
    Page<Blog> findAllBlog(Pageable pageable);
}
