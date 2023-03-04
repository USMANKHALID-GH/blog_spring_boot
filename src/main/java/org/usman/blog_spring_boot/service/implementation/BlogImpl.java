package org.usman.blog_spring_boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usman.blog_spring_boot.Error.IdNotFoundException;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CatDto;
import org.usman.blog_spring_boot.mapperDto.MapperDto;
import org.usman.blog_spring_boot.respository.BlogRepository;
import org.usman.blog_spring_boot.respository.CatRepository;
import org.usman.blog_spring_boot.service.serviceInterface.BlogInt;
import org.usman.blog_spring_boot.service.serviceInterface.CatInter;
import org.usman.blog_spring_boot.utility.Blog;
import org.usman.blog_spring_boot.utility.Cat;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogImpl implements BlogInt, CatInter {



    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CatRepository catRepository;
    @Autowired
    private MapperDto mapperDto;

    @Override
    public Cat saveCat(Cat cat) {
        System.out.println("====================================================");
        System.out.println(cat.getCategory());
        return  catRepository.save(cat);
    }

    @Override
    public CatDto findCat(Long id) {

        Optional<Cat> cat=catRepository.findById(id);
        if(cat.isPresent()){
        return mapperDto.entityToCatDto(cat.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);

    }

    @Override
    public String updateCat(Long id, CatDto catDto) {
        Optional<Cat> cat=catRepository.findById(id);
        if(cat.isPresent()){
            Cat cat2=cat.map(cat1 -> {
                if(catDto.getCategory()!="" || catDto.getCategory()!=null){
            cat1.setCategory(catDto.getCategory());}

            return cat1;


            }).get();
        System.out.println(cat2.getCategory());
        catRepository.save(cat2);


        return "Updated";}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }

    @Override
    public CatDto findByName(String name) {

        Cat cat=catRepository.findCatByCategory(name);
        System.out.println(cat.getCategory()+"-------------------------------");
        return mapperDto.entityToCatDto(cat);

    }

    @Override
    public String deleteCat(Long id) {
        if(catRepository.findById(id).isPresent()){
           catRepository.deleteById(id);
        return "deleted"
                ;}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }

    @Override
    public List<CatDto> getAll() {
        return mapperDto.entityCatToDto(catRepository.findAll());
    }

    @Override
    public List<BlogGeneralDto> searchInCentent(String string) {

        Optional<List<Blog>> blogs=blogRepository.findBySearchParameter(string);
        System.out.println(blogs.get()+"000000000000000000000000000000000000000000000000000");
        List<BlogGeneralDto> blogGeneralDtos=mapperDto.entityToDto(blogs.get());
        return blogGeneralDtos;


    }

    @Override
    public BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto, Long id) {
        Optional<Cat> cat =catRepository.findById(id);
        System.out.println("...................????????????"+cat.get());
        if(cat.isPresent()){

            Blog blog =mapperDto.dtoToEntity(blogGeneralDto);
            blog.setDateCreated(LocalDate.now());
            blog.setCat(cat.get());
            System.out.println("................"+blog.getDateCreated());
            blogRepository.save(blog);
            return  mapperDto.entityToDto(blog);
        }
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }

    @Override
    public List<BlogGeneralDto> showAll() {
        System.out.println("Service Class!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<Blog> blogs=blogRepository.findAll();
        System.out.println("list is gotten......................................");
        List<BlogGeneralDto> blogDtos=mapperDto.entityToDto(blogs);
        return blogDtos;
    }

    @Override
    public BlogGeneralDto blogFindById(Long id) {
        Optional<Blog> blog=blogRepository.findById(id);
        if(blog.isPresent()){
        return  mapperDto.entityToDto(blog.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);

    }

    @Override
    public List<BlogDto> findAllBlod() {
        List<Blog> blogs=blogRepository.findAll();
        List<BlogDto> blogDtos=mapperDto.entityToBlogDto(blogs);
        return blogDtos;
    }

    @Override
    public String updateBlog(Long id,BlogGeneralDto blogGeneralDto) {
        Optional<Blog> cat=blogRepository.findById(id);
        if(cat.isPresent()){


        Blog blog1=cat.map(blog -> {
            if(blogGeneralDto.getContent()!="" || blogGeneralDto.getContent()!=""){
            blog.setContent(blogGeneralDto.getContent());}
            if(blogGeneralDto.getTitle()!="" || blogGeneralDto.getContent()!=null){
            blog.setTitle(blogGeneralDto.getTitle());}
            if(blogGeneralDto.getImage()!=""||blogGeneralDto.getImage()!=null){
                blog.setImage(blogGeneralDto.getImage());
            }

            return blog;
        }).get();

        blogRepository.save(blog1);


        return "Updated";}
        else throw new IdNotFoundException("there is no such Id "+id);
    }

    @Override
    public String deleteBlog(Long id) {

        if(blogRepository.findById(id).isPresent()){
         blogRepository.deleteById(id);
        return "Deleted";}
        else throw new IdNotFoundException("there is no such Id "+id);
    }
}
