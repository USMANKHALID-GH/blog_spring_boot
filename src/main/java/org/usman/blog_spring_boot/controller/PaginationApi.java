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

import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.service.implementation.Pagination_Sorting;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/pi/v1/page")
public class PaginationApi {
    @Autowired
    private Pagination_Sorting service;

    @Autowired
    private BlogMapper mapper;


    @PostMapping
    public ResponseEntity<BaseResponseDto> create(@RequestBody BlogDto categoryDto) {
        service.create(mapper.toEntity(categoryDto));
        return ResponseEntity.ok(BaseResponseDto.builder().message("Category oluşturma işlemi başarılı olarak tamamlanmıştır").build());
    }


    @GetMapping("/blog")
    public ResponseEntity<Page<BlogDto>> findAll(Pageable pageable, @RequestParam(required = false, name = "search") int search) {
        return ResponseEntity.ok(new PageImpl<>(mapper.toDto(service.findAll(pageable,search).getContent())));
    }

}
