package org.usman.blog_spring_boot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CatDto;
import org.usman.blog_spring_boot.service.implementation.BlogImpl;


import java.util.List;

@RestController
@RequestMapping("v1/blog")
public class BlogController {

    @Autowired
    private BlogImpl blog;

    @GetMapping("/")
    public  String hello(){
        return  "hello!!!!!!!!!!!";
    }

    @PostMapping("/save/{id}")
    public BlogGeneralDto createBlog(@RequestBody @Valid BlogGeneralDto blogGeneralDto, @PathVariable("id") Long id){
        System.out.println(".........................."+id);
        System.out.println("///////////////////////////////////"+blogGeneralDto.toString());
        return blog.saveBlog(blogGeneralDto,id);
    }

    @GetMapping("/all")
    public List<BlogGeneralDto> showAllBlog(){
        return blog.showAll();
    }

    @GetMapping("/find/{id}")
    public BlogGeneralDto findBlogById(@PathVariable("id") Long id){
        return blog.blogFindById(id);
    }

    @GetMapping("/allblog")
    public List<BlogDto> AllBlog(){
        return blog.findAllBlod();
    }

    @PutMapping("/update/{id}")
    public  String updatecat(@PathVariable("id") Long id,  @RequestBody @Valid BlogGeneralDto catDto){


          return blog.updateBlog(id,catDto);

    }

    @DeleteMapping("/delete/{id}")
    public String deleteByCatId(@PathVariable("id") Long name){
        return blog.deleteBlog(name);
    }

    @GetMapping("/search/{sentence}")
    public  List<BlogGeneralDto> searchForPhrase(@PathVariable("sentence") String sentence){
        System.out.println("/////////////////////////////////////////////"+sentence);
        return  blog.searchInCentent(sentence);
    }





}
