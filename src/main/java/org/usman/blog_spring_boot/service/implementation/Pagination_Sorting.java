package org.usman.blog_spring_boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import org.usman.blog_spring_boot.error.IdNotFoundException;
import org.usman.blog_spring_boot.model.AbstractModel;
import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.respository.BlogRepository;

import java.util.Optional;

@Service
public class Pagination_Sorting {
    @Autowired
    private BlogRepository blogRepository;


    public void create(Blog toEntity) {
         blogRepository.save(toEntity);
    }

    public Page<Blog> findAllByCategory(Pageable pageable, int search) {
        return   blogRepository.findAllByCategory(pageable,search);

    }


    public  Blog findBlogBYId(Long integer) {
        return Optional.ofNullable(blogRepository.findById(integer)).get().orElseThrow(
                ()->new IdNotFoundException("there is such Id in our System "+integer)
        );
    }

    public  void deleteBlogBYId(Long integer) {
        Blog blog=  Optional.ofNullable(blogRepository.findById(integer)).get().orElseThrow(
                ()->new IdNotFoundException("there is such Id in our System "+integer)
        );

        blogRepository.delete(blog);


    }
}
