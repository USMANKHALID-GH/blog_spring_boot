package org.usman.blog_spring_boot.service.serviceInterface;

import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CatDto;
import org.usman.blog_spring_boot.utility.Cat;

import java.util.List;


public interface CatInter {

      Cat saveCat(Cat cat);

      CatDto  findCat(Long id);

   String updateCat(Long id ,CatDto catDto);

   CatDto findByName(String name);

   String deleteCat(Long id);

    List<CatDto> getAll();


}
