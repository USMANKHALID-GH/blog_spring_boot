package org.usman.blog_spring_boot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.usman.blog_spring_boot.Error.IdNotFoundException;
import org.usman.blog_spring_boot.dto.CatDto;
import org.usman.blog_spring_boot.mapperDto.MapperDto;
import org.usman.blog_spring_boot.service.implementation.BlogImpl;
import org.usman.blog_spring_boot.utility.Cat;

import java.util.List;

@RestController
@RequestMapping("v1/blog/cat")
public class CatController {

    @Autowired
    private BlogImpl blog;

    @Autowired
    private MapperDto mapperDto;

//saving cat api
    @PostMapping("/save")
    public String saveCat(@RequestBody @Valid CatDto catDto){

        Cat cat=mapperDto.dtoToCatEntity(catDto);


        blog.saveCat(cat
        );
        return "saved";
    }


//    find cat by id api
    @GetMapping("/find/{id}")
    public  CatDto findCatById(@PathVariable("id") Long id)throws IdNotFoundException{

         return  blog.findCat(id);

    }
//updating cat api
    @PutMapping("/update/{id}")
    public  String updatecat(@PathVariable("id") Long id,
                             @RequestBody @Valid CatDto catDto)throws IdNotFoundException {
        System.out.println(id+ "...................."+catDto.getCategory());

        return  blog.updateCat(id,catDto);

    }

//    getting cat by name api
    @GetMapping("/findby/{name}")
    public CatDto findByCatName(@PathVariable("name") String name){
        return blog.findByName(name);
    }

//    deleting cat using id api
    @DeleteMapping("/delete/{id}")
    public String deleteByCatId(@PathVariable("id") Long name)throws IdNotFoundException{
        return blog.deleteCat(name);
    }

//    getting  all cat api
    @GetMapping("/all")
    public List<CatDto> getAllCat(){
       return blog.getAll();
    }
}
