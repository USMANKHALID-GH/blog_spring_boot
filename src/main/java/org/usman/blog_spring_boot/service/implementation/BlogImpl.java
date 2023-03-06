package org.usman.blog_spring_boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.usman.blog_spring_boot.Error.IdNotFoundException;
import org.usman.blog_spring_boot.Error.PhraseNotFoundEXception;
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


//    saving category to database
    @Override
    public Cat saveCat(Cat cat) {

        cat.setCategory(cat.getCategory().toLowerCase());
        return  catRepository.save(cat);
    }

//    find category by id
    @Override
    public CatDto findCat(Long id) {

        Optional<Cat> cat=catRepository.findById(id);
        if(cat.isPresent()){
        return mapperDto.entityToCatDto(cat.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);

    }

//    updating category using cat id
    @Override
    public String updateCat(Long id, CatDto catDto) {
        Optional<Cat> cat=catRepository.findById(id);
        if(cat.isPresent()){
            Cat cat2=cat.map(cat1 -> {
                if(catDto.getCategory()!="" || catDto.getCategory()!=null){
            cat1.setCategory(catDto.getCategory().toLowerCase());}

            return cat1;


            }).get();
        catRepository.save(cat2);


        return "Updated";}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }


//    find by using cat name
    @Override
    public CatDto findByName(String name) {

        Cat cat=catRepository.findCatByCategory(name.toLowerCase());

        return mapperDto.entityToCatDto(cat);

    }

//    delete category using category id
    @Override
    public String deleteCat(Long id) {
        if(catRepository.findById(id).isPresent()){
           catRepository.deleteById(id);
        return "deleted"
                ;}
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }


//    get all category
    @Override
    public List<CatDto> getAll() {
        return mapperDto.entityCatToDto(catRepository.findAll());
    }

//    searching for a word or phrase in blog content
    @Override
    public List<BlogGeneralDto> searchInCentent(String string) {

        List<Blog> blogs= blogRepository.findByContentContaining(string.toLowerCase().trim());
        System.out.println("......................."+blogs.isEmpty());
        if(!blogs.isEmpty()) {

            List<BlogGeneralDto> blogGeneralDtos = mapperDto.entityToDto(blogs);
            return blogGeneralDtos;
        }
        else
            throw new PhraseNotFoundEXception("there is no such phrase in our database "+string);

    }


//    searching for full title of part of title in the blog table
    @Override
    public List<BlogGeneralDto> searchInTitle(String string) {
        List<Blog> blogs =blogRepository.findByTitleContaining(string.toLowerCase().trim());
        if(!blogs.isEmpty()){
            return mapperDto.entityToDto(blogs);
        }
        else
            throw  new PhraseNotFoundEXception("there is not such phrase in our database"+string);

    }

//    search in title and content
    @Override
    public List<BlogGeneralDto> searchAll(String string ,String string1) {
        List<Blog> blogs=blogRepository.findByTitleOrContentContaining(string,string1);
        if(blogs.isEmpty()){
        return  mapperDto.entityToDto(blogs);}
        else
            throw  new PhraseNotFoundEXception("this phrase cant not be found: "+ string);
    }


//    find by cat id
    @Override
    public List<BlogGeneralDto> findByCat(int integer) {
        List<Blog> blogs= blogRepository.findAllByCat(integer);
        if(blogs.isEmpty()){
        return mapperDto.entityToDto(blogs);}
        else
            throw  new PhraseNotFoundEXception("there is no id associatied with this : "+integer);
    }


    //    saving a blog
    @Override
    public BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto, Long id) {
        Optional<Cat> cat =catRepository.findById(id);
        System.out.println(cat.isPresent()+"ooooooooooooooooooooooooooooooooooooooooo");
        if(cat.isPresent()){

            Blog blog =mapperDto.dtoToEntity(blogGeneralDto);
            blog.setContent(blog.getContent().toLowerCase());
            blog.setTitle(blog.getTitle());
            blog.setDateCreated(LocalDate.now());

            blog.setCat(cat.get());

            blogRepository.save(blog);
            return  mapperDto.entityToDto(blog);
        }
        else
            throw new IdNotFoundException("there is no such Id "+id);
    }

//    shall all blogs
    @Override
    public List<BlogGeneralDto> showAll() {
        System.out.println("Service Class!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        List<Blog> blogs=blogRepository.findAll();
        System.out.println("list is gotten......................................");
        List<BlogGeneralDto> blogDtos=mapperDto.entityToDto(blogs);
        return blogDtos;
    }

//    showing blog using blog id
    @Override
    public BlogGeneralDto blogFindById(Long id) {
        Optional<Blog> blog=blogRepository.findById(id);
        if(blog.isPresent()){
        return  mapperDto.entityToDto(blog.get());}
        else
            throw new IdNotFoundException("there is no such Id "+id);

    }

//    shall all blog but has an error yet to be solved
    @Override
    public List<BlogDto> findAllBlod() {
        List<Blog> blogs=blogRepository.findAll();
        List<BlogDto> blogDtos=mapperDto.entityToBlogDto(blogs);
        return blogDtos;
    }

//updating a blog
    @Override
    public String updateBlog(Long id,BlogGeneralDto blogGeneralDto) {
        Optional<Blog> cat=blogRepository.findById(id);
        if(cat.isPresent()){


        Blog blog1=cat.map(blog -> {
            if(blogGeneralDto.getContent()!="" || blogGeneralDto.getContent()!=""){
            blog.setContent(blogGeneralDto.getContent().toLowerCase());}
            if(blogGeneralDto.getTitle()!="" || blogGeneralDto.getContent()!=null){
            blog.setTitle(blogGeneralDto.getTitle().toLowerCase());}
            if(blogGeneralDto.getImage()!=""||blogGeneralDto.getImage()!=null){
                blog.setImage(blogGeneralDto.getImage().toLowerCase());
            }

            return blog;
        }).get();

        blogRepository.save(blog1);


        return "Updated";}
        else throw new IdNotFoundException("there is no such Id "+id);
    }

//    deleting a blog by id
    @Override
    public String deleteBlog(Long id) {

        if(blogRepository.findById(id).isPresent()){
         blogRepository.deleteById(id);
        return "Deleted";}
        else throw new IdNotFoundException("there is no such Id "+id);
    }





}
