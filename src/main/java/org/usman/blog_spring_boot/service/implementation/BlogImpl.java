package org.usman.blog_spring_boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.usman.blog_spring_boot.Error.IdNotFoundException;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CatDto;
import org.usman.blog_spring_boot.mapperDto.MapperDto;
import org.usman.blog_spring_boot.respository.BlogRepository;
import org.usman.blog_spring_boot.respository.CatRepository;
import org.usman.blog_spring_boot.service.serviceInterface.BlogInt;
import org.usman.blog_spring_boot.service.serviceInterface.CatInter;
import org.usman.blog_spring_boot.utility.Blog;
import org.usman.blog_spring_boot.utility.Cat;

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
        System.out.println("/////////////////////"+id);
        Optional<Cat> cat=catRepository.findById(id);
        return mapperDto.entityToCatDto(cat.get());
    }

    @Override
    public String updateCat(Long id, CatDto catDto) {
        Optional<Cat> cat=catRepository.findById(id);
        System.out.println(cat.get().getCategory()+"222222222222222222222222222222222222");

        Cat cat2=cat.map(cat1 -> {
            cat1.setCategory(catDto.getCategory());
            return cat1;
        }).get();
        System.out.println(cat2.getCategory());
        catRepository.save(cat2);


        return "Updated";
    }

    @Override
    public CatDto findByName(String name) {

        Cat cat=catRepository.findCatByCategory(name);
        System.out.println(cat.getCategory()+"-------------------------------");
        return mapperDto.entityToCatDto(cat);

    }

    @Override
    public String deleteCat(Long id) {
          catRepository.deleteById(id);
        return "deleted"
                ;
    }

    @Override
    public List<CatDto> getAll() {
        return mapperDto.entityCatToDto(catRepository.findAll());
    }

    @Override
    public BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto) {
        Blog blog =mapperDto.dtoToEntity(blogGeneralDto);
        blogRepository.save(blog);
        return  mapperDto.entityToDto(blog);
    }

    @Override
    public List<BlogGeneralDto> showAll() {
        return mapperDto.entityToDto(blogRepository.findAll());
    }

    @Override
    public BlogGeneralDto blogFindById(Long id) {
        Optional<Blog> blog=blogRepository.findById(id);
        if(blog.isPresent()){
        return  mapperDto.entityToDto(blog.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }
}
