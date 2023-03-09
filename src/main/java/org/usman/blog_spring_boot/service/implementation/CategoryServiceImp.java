package org.usman.blog_spring_boot.service.implementation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.error.IdNotFoundException;
import org.usman.blog_spring_boot.error.PhraseNotFoundEXception;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.dto.CategoryDto;
import org.usman.blog_spring_boot.mapper.EntityMapper;
import org.usman.blog_spring_boot.mapper.MapperDto;
import org.usman.blog_spring_boot.respository.BlogRepository;
import org.usman.blog_spring_boot.respository.CategoryRepository;
import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.model.Cat;

import java.util.List;
import java.util.Optional;
import org.usman.blog_spring_boot.service.BlogService;
import   org.usman.blog_spring_boot.service.CategoryService;



@Slf4j
@Service
public class CategoryServiceImp implements BlogService, CategoryService {



    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MapperDto mapper;

    @Autowired
    private EntityMapper entityMapper;


    @Override
    public Cat saveCategory(Cat cat) {

        cat.setCategory(cat.getCategory().toLowerCase());
        return  categoryRepository.save(cat);
    }


    @Override
    public CategoryDto findCategory(Long id) {

        Optional<Cat> cat= categoryRepository.findById(id);
        if(cat.isPresent()){
        return mapper.entityToCatDto(cat.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);

    }


    @Override
    public String updateCategory(Long id, CategoryDto categoryDto) {
        Optional<Cat> cat= categoryRepository.findById(id);
        if(cat.isPresent()){
            Cat cat2=cat.map(cat1 -> {
                if(categoryDto.getCategory()!="" || categoryDto.getCategory()!=null){
            cat1.setCategory(categoryDto.getCategory().toLowerCase());}

            return cat1;


            }).get();
        categoryRepository.save(cat2);


        return "Updated";}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }



    @Override
    public CategoryDto findByCategoryName(String name) {

        Cat cat= categoryRepository.findCatByCategory(name.toLowerCase());

        return mapper.entityToCatDto(cat);

    }


    @Override
    public String deleteCategory(Long id) {
        if(categoryRepository.findById(id).isPresent()){
           categoryRepository.deleteById(id);
        return "deleted"
                ;}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }



    @Override
    public List<CategoryDto> getAllCategory() {
        return mapper.entityCatToDto(categoryRepository.findAll());
    }


    @Override
    public List<BlogGeneralDto> searchInContent(String string) {

        List<Blog> blogs= blogRepository.findByContentContaining(string.toLowerCase().trim());
        System.out.println("......................."+blogs.isEmpty());
        if(!blogs.isEmpty()) {

            List<BlogGeneralDto> blogGeneralDtos = mapper.entityToDto(blogs);
            return blogGeneralDtos;
        }
        else
            throw new PhraseNotFoundEXception("there is no such phrase in our database "+string);

    }



    @Override
    public List<BlogGeneralDto> searchInTitle(String string) {
        List<Blog> blogs =blogRepository.findByTitleContaining(string.toLowerCase().trim());
        if(!blogs.isEmpty()){
            return mapper.entityToDto(blogs);
        }
        else
            throw  new PhraseNotFoundEXception("there is not such phrase in our database"+string);

    }


    @Override
    public List<BlogGeneralDto> searchTitleOrContent(String string , String string1) {
        List<Blog> blogs=blogRepository.findByTitleOrContentContaining(string,string1);
        if(blogs.isEmpty()){
        return  mapper.entityToDto(blogs);}
        else
            throw  new PhraseNotFoundEXception("this phrase cant not be found: "+ string);
    }



    @Override
    public List<BlogGeneralDto> findByCat(int integer) {
        List<Blog> blogs= blogRepository.findAllByCat(integer);
        if(blogs.isEmpty()){
        return mapper.entityToDto(blogs);}
        else
            throw  new PhraseNotFoundEXception("there is no id associatied with this : "+integer);
    }




    @Override
    public BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto, Long id) {


        Optional<Cat> cat = categoryRepository.findById(id);
        

        if(cat.isPresent()){

            Blog blog = mapper.dtoToEntity(blogGeneralDto);
            blog.setContent(blog.getContent().toLowerCase());
            blog.setTitle(blog.getTitle());
//            blog.setDateCreated(LocalDate.now());

            blog.setCat(cat.get());

            Blog blog1=blogRepository.save(blog);
            log.info(blog1+"bloggggggggggggggggg");
            return  mapper.entityToDto(blog);
        }
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }


    @Override
    public List<BlogGeneralDto> showAllBlog() {

        List<Blog> blogs=blogRepository.findAll();

        List<BlogGeneralDto> blogDtos= mapper.entityToDto(blogs);
        return blogDtos;
    }


    @Override
    public BlogGeneralDto FindByBlogId(Long id) {
        Optional<Blog> blog=blogRepository.findById(id);
        if(blog.isPresent()){
        return  mapper.entityToDto(blog.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);

    }


    @Override
    public List<Blog> findAllBlog() {
        List<Blog> blogs=blogRepository.findAll();
        for (Blog list:blogs){
            log.info("////////////////////////////////////"+list);
        }

        return blogs;
    }


    @Override
    public String updateBlog(Long id,BlogGeneralDto blogGeneralDto) {
        Optional<Blog> cat=blogRepository.findById(id);
        if(cat.isPresent()){


        Blog blog1=cat.map(blog -> {
            if(!blogGeneralDto.getContent().isEmpty()){
            blog.setContent(blogGeneralDto.getContent().toLowerCase());}
            if(!blogGeneralDto.getTitle().isEmpty()){
            blog.setTitle(blogGeneralDto.getTitle().toLowerCase());}
            if(!blogGeneralDto.getImage().isEmpty()){
                blog.setImage(blogGeneralDto.getImage().toLowerCase());
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


    @Override
    public List<BlogDto> findByCategory(int integer) {
           List<Blog> blogs=blogRepository.findAllByCategory(integer).getContent();
        return entityMapper.toDto(blogs);
    }


}
