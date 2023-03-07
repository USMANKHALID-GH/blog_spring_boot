package org.usman.blog_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.usman.blog_spring_boot.dto.BlogGeneralDto;
import org.usman.blog_spring_boot.service.implementation.Pagination_Sorting;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class PaginationApi {
    @Autowired
    private Pagination_Sorting pagination_sorting;

    //   pagination and sorting

    @GetMapping(path = {"/get/{offset}/{pageSize}/{field}/{order}","/get"
    ,"/get/{offset}", "/get/{offset}/{pageSize}","/get/{offset}/{pageSize}/{field}"})
    public List<BlogGeneralDto> getAll(@PathVariable(value = "offset", required = false) Integer offset,
                                       @PathVariable(value = "pageSize",required = false)  Integer pageSize,
                                       @PathVariable(value = "field",required = false )String field,
                                       @PathVariable(value = "order",required = false) String order
                                       ){

        if(order==null){
            return  pagination_sorting.getAll(offset,pageSize,field,"desc");
        }
        else
            return  pagination_sorting.getAll(offset,pageSize,field,order);

    }

    @GetMapping(path = {"/get1","/get1/{id}"})
    public  String get(@PathVariable(value = "id", required = false) Integer id){
        if(id==null){
        return "just testing  ";}
        else
            return id+" yeahhhh";
    }


}
