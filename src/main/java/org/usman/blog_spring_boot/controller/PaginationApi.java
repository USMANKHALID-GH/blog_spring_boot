package org.usman.blog_spring_boot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.dto.BaseResponseDto;
import org.usman.blog_spring_boot.dto.BlogDto;


import org.usman.blog_spring_boot.mapper.BlogMapper;


import org.usman.blog_spring_boot.service.PaginationService;




@Slf4j
@RestController
@RequestMapping("/pi/v1/page")
public class PaginationApi {
    @Autowired
    private PaginationService service;

    @Autowired
    private BlogMapper mapper;




    @PostMapping("/")
    public ResponseEntity<BaseResponseDto> create(@RequestBody BlogDto categoryDto) {
        service.create(mapper.toEntity(categoryDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("Category oluşturma işlemi başarılı olarak tamamlanmıştır").build());
    }


    @GetMapping("/blog")
    public ResponseEntity<Page<BlogDto>> findAllBlogByCategory(Pageable pageable, @RequestParam( name = "catId") int catId) {
        return ResponseEntity.ok(new PageImpl<>(mapper.toDto(service.findAllByCategory(pageable,catId).getContent())));
    }

    @GetMapping("/")
    public  ResponseEntity<Page<BlogDto>> findAll(Pageable pageable){

        return   ResponseEntity.ok(new PageImpl<>(mapper.toDto(service.findAllBlog(pageable).getContent())));

    }

    @GetMapping("/{id}")
    public  ResponseEntity<BlogDto> findBlogById(@PathVariable("id") Long id){
        return ResponseEntity.ok(mapper.toDto(service.findBlogBYId(id)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponseDto> deleteBlog(@PathVariable("id") Long id) {
        service.deleteBlogBYId(id);
        return ResponseEntity.ok(BaseResponseDto.builder().message("blog başarılı olarak silinmıştır").build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponseDto> updateBlog(@PathVariable("id") Long id, @RequestBody BlogDto blogDto) {

        service.updateBlogId(id ,blogDto);
        return ResponseEntity.ok(BaseResponseDto.builder().message("blog başarılı olarak guncelenmıştır").build());
    }




}
