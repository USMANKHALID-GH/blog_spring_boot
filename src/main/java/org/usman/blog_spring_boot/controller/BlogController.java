package org.usman.blog_spring_boot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.error.IdNotFoundException;
import org.usman.blog_spring_boot.error.PhraseNotFoundEXception;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.mapper.BlogMapper;
import org.usman.blog_spring_boot.service.implementation.CategoryServiceImp;


import java.util.List;

@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {

    @Autowired
    private CategoryServiceImp service;
    @Autowired
    private BlogMapper mapper;



    @PostMapping("/{id}")
    public ResponseEntity<BlogGeneralDto> createBlog(
                                      @RequestBody @Valid BlogGeneralDto blogGeneralDto,
                                     @PathVariable("id") Long id)throws IdNotFoundException{

         return  new ResponseEntity<>(service.saveBlog(blogGeneralDto,id), HttpStatus.CREATED);
}

    @GetMapping("/all")
    public ResponseEntity<List<BlogDto>> AllBlog(){

        return  new ResponseEntity<>(mapper.toDto(service.findAllBlog()),HttpStatus.ACCEPTED);

    }

    @GetMapping("/")
    public ResponseEntity<List<BlogGeneralDto>>showAllBlog(){
        return  new ResponseEntity<>(service.showAllBlog(),HttpStatus.ACCEPTED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BlogGeneralDto> findBlogById(@PathVariable("id") Long id){
        return new ResponseEntity<>(service.FindByBlogId(id),HttpStatus.ACCEPTED);
    }






    @PutMapping("/{id}")
    public  ResponseEntity<String> updateCategory(@PathVariable("id") Long id,
                             @RequestBody @Valid BlogGeneralDto catDto){


          return new ResponseEntity<>(service.updateBlog(id,catDto),HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByCatId(@PathVariable("id") Long name) throws IdNotFoundException {
        return new ResponseEntity<>(service.deleteBlog(name),HttpStatus.ACCEPTED);
    }


    @GetMapping("/{sentence}/search")
    public  ResponseEntity<List<BlogGeneralDto>> searchForPhrase(@PathVariable("sentence") String sentence){
            return  new ResponseEntity<>(service.searchInContent(sentence) ,HttpStatus.ACCEPTED);
    }


        @GetMapping("/{string}/title")
        public ResponseEntity<List<BlogGeneralDto>> SearchTitle(@PathVariable("string") String string) {
         return new ResponseEntity<>(service.searchInTitle(string) , HttpStatus.ACCEPTED);
        }


    @GetMapping("/{string}/title_content")
    public ResponseEntity<List<BlogGeneralDto>> SearchAny(@PathVariable("string") String string)  {
        return  new ResponseEntity<>(service.searchTitleOrContent(string,string),HttpStatus.ACCEPTED);
    }



    @GetMapping("/{id}/category")
    public ResponseEntity<List<BlogGeneralDto>> SearchByCat(@PathVariable("id") int id)  {
        return  new ResponseEntity<>(service.findByCat(id),HttpStatus.ACCEPTED);
    }



}
