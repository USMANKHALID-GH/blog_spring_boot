package org.usman.blog_spring_boot.service;

import org.usman.blog_spring_boot.dto.CategoryDto;
import org.usman.blog_spring_boot.model.Cat;

import java.util.List;


public interface CategoryService {

      Cat saveCategory(Cat cat);

      CategoryDto findCategory(Long id);

   String updateCategory(Long id , CategoryDto categoryDto);

   CategoryDto findByCategoryName(String name);

   String deleteCategory(Long id);

    List<CategoryDto> getAllCategory();




}
