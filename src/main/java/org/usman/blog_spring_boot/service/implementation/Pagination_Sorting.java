package org.usman.blog_spring_boot.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.mapper.MapperDto;
import org.usman.blog_spring_boot.respository.BlogRepository;
import org.usman.blog_spring_boot.model.Blog;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Pagination_Sorting {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private MapperDto mapperDto;


    public List<BlogGeneralDto>  getAll(Integer offset,Integer pageSize,String field, String order){
        Page<Blog> page=null;
        if(offset==null){
           offset=0;
        }
        if(pageSize==null){
            pageSize=5;
        }
        if(field==null){
            field="id";
        }

        if(field==null){

            page=blogRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(field)));
        }


        else if(order.equalsIgnoreCase("asc")){

            page=blogRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.ASC,field)));

        }
        else {
            page=blogRepository.findAll(PageRequest.of(offset,pageSize).withSort(Sort.by(Sort.Direction.DESC,field)));

        }




        return  mapperDto.entityToDto(page.stream().collect(Collectors.toList()));
    }



}
