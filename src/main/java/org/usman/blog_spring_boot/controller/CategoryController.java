package org.usman.blog_spring_boot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.error.IdNotFoundException;
import org.usman.blog_spring_boot.dto.CategoryDto;
import org.usman.blog_spring_boot.mapper.MapperDto;
import org.usman.blog_spring_boot.service.implementation.CategoryServiceImp;



import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImp service;

    @Autowired
    private MapperDto mapper;


    @PostMapping("/")
    public ResponseEntity<String> saveCategory(@RequestBody @Valid CategoryDto categoryDto){
        service.saveCategory(mapper.dtoToCatEntity(categoryDto));
        return new ResponseEntity<>("saved", HttpStatus.CREATED);
    }



    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable("id") Long id){

         return  new ResponseEntity<>(service.findCategory(id),HttpStatus.ACCEPTED);

    }

    @PutMapping("/{id}")
    public  ResponseEntity<String> updatecat(@PathVariable("id") Long id,
                             @RequestBody @Valid CategoryDto categoryDto){
      log.info("........................."+id);

        return  new ResponseEntity<>(service.updateCategory(id, categoryDto),HttpStatus.CREATED);

    }


    @GetMapping("/{name}/category_name")
    public ResponseEntity<CategoryDto> findByCategoryName(@PathVariable("name") String name){
        return new ResponseEntity<>(service.findByCategoryName(name),HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByCatId(@PathVariable("id") Long name){
        return new ResponseEntity<>(service.deleteCategory(name),HttpStatus.ACCEPTED);
    }


    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCat(){
       return  new ResponseEntity<>(service.getAllCategory(),HttpStatus.ACCEPTED);
    }
}
