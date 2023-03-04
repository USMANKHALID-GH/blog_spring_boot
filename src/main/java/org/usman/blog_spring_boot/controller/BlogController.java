package org.usman.blog_spring_boot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.service.implementation.BlogImpl;


import java.util.List;

@RestController
@RequestMapping(name = "v1/blog")
public class BlogController {

    @Autowired
    private BlogImpl blog;

    @PostMapping("/save")
    public BlogGeneralDto createBlog(@RequestBody @Valid BlogGeneralDto blogGeneralDto){
        return blog.saveBlog(blogGeneralDto);
    }

    @PostMapping("/all")
    public List<BlogGeneralDto> showAllBlog(){
        return blog.showAll();
    }

    @PostMapping("/find/{id}")
    public BlogGeneralDto findBlogById(@PathVariable("id") Long id){
        return blog.blogFindById(id);
    }





}
