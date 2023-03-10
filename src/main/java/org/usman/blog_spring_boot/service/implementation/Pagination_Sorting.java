package org.usman.blog_spring_boot.service.implementation;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.error.IdNotFoundException;
import org.usman.blog_spring_boot.model.AbstractModel;
import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.respository.BlogRepository;

import java.util.Optional;

@Service
public class Pagination_Sorting {
    @Autowired
    private BlogRepository blogRepository;


    public void create(Blog toEntity) {
         blogRepository.save(toEntity);
    }

    public Page<Blog> findAllByCategory(Pageable pageable, int search) {
        return   blogRepository.findAllByCategory(pageable,search);

    }


    public  Blog findBlogBYId(Long integer) {
        return Optional.ofNullable(blogRepository.findById(integer)).get().orElseThrow(
                ()->new IdNotFoundException("there is such Id in our System "+integer)
        );
    }

    public  void deleteBlogBYId(Long integer) {
        Blog blog=  Optional.ofNullable(blogRepository.findById(integer)).get().orElseThrow(
                ()->new IdNotFoundException("there is such Id in our System "+integer)
        );

        blogRepository.delete(blog);


    }

    public  void updateBlogId(Long integer, BlogDto blogDto){
        Blog blog=null;
        try {
             blog= blogRepository.findById(integer)
                    .map( blog1 -> {
                        if(!StringUtils.isEmpty(blogDto.getContent())){
                            blog1.setContent(blogDto.getContent().trim());
                        }
                        if(!StringUtils.isEmpty(blogDto.getImage())){
                            blog1.setImage(blogDto.getImage());
                        }
                        if(!StringUtils.isEmpty(blogDto.getTitle())){
                            blog1.setTitle(blogDto.getTitle().trim());
                        }
                        return blog1;
                    }).get();
        }catch (IdNotFoundException e){
            throw new IdNotFoundException("There is no such id in oy "+ integer);
        }
       blogRepository.save(blog);

    }
}
