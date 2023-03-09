package org.usman.blog_spring_boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import org.usman.blog_spring_boot.model.AbstractModel;
import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.respository.BlogRepository;

@Service
public class Pagination_Sorting {
    @Autowired
    private BlogRepository blogRepository;


    public void create(Blog toEntity) {
         blogRepository.save(toEntity);
    }

    public Page<Blog> findAll(Pageable pageable, int search) {
        return blogRepository.findAll(pageable,search);
    }
}
