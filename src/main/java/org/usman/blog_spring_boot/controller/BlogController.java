package org.usman.blog_spring_boot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.Error.IdNotFoundException;
import org.usman.blog_spring_boot.Error.PhraseNotFoundEXception;
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
//main page
    @GetMapping("/")
    public  String hello(){
        return  "hello!!!!!!!!!!!";
    }

//    saving blog APi
    @PostMapping("/save/{id}")
    public BlogGeneralDto createBlog(@RequestBody @Valid BlogGeneralDto blogGeneralDto,
                                     @PathVariable("id") Long id)throws IdNotFoundException{
        System.out.println(".........................."+id);

        return blog.saveBlog(blogGeneralDto,id);
    }

//    showing all blog api
    @GetMapping("/all")
    public List<BlogGeneralDto> showAllBlog(){
        return blog.showAll();
    }

//    get blog by id api
    @GetMapping("/find/{id}")
    public BlogGeneralDto findBlogById(@PathVariable("id") Long id)throws IdNotFoundException{
        return blog.blogFindById(id);
    }

//    and error with this api getting all blog
    @GetMapping("/allblog")
    public List<BlogDto> AllBlog(){
        return blog.findAllBlod();
    }


//    updating blog api
    @PutMapping("/update/{id}")
    public  String updatecat(@PathVariable("id") Long id,
                             @RequestBody @Valid BlogGeneralDto catDto)throws IdNotFoundException{


          return blog.updateBlog(id,catDto);

    }

//    deleting blog api
    @DeleteMapping("/delete/{id}")
    public String deleteByCatId(@PathVariable("id") Long name) throws IdNotFoundException {
        return blog.deleteBlog(name);
    }

//    searching in centent api
    @GetMapping("/search/{sentence}")
    public  List<BlogGeneralDto> searchForPhrase(@PathVariable("sentence") String sentence) throws PhraseNotFoundEXception {




        return  blog.searchInCentent(sentence);
    }

//searching for a title api
        @GetMapping("findby/{string}")
        public List<BlogGeneralDto> SearchTitle(@PathVariable("string") String string) throws  PhraseNotFoundEXception{
         return  blog.searchInTitle(string);
        }


    //searching for in both title and content
    @GetMapping("find/any/{string}")
    public List<BlogGeneralDto> SearchAny(@PathVariable("string") String string) throws  PhraseNotFoundEXception {
        return  blog.searchAll(string,string);
    }


    //searching for in both title and content
    @GetMapping("findby/cat/{id}")
    public List<BlogGeneralDto> SearchByCat(@PathVariable("id") int id) throws IdNotFoundException {
        return  blog.findByCat(id);
    }



}
