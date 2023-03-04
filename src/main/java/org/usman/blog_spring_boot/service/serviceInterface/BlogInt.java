package org.usman.blog_spring_boot.service.serviceInterface;

import org.usman.blog_spring_boot.dto.BlogGeneralDto;

import java.util.List;

public interface BlogInt {
    BlogGeneralDto saveBlog(BlogGeneralDto blogGeneralDto);

    List<BlogGeneralDto> showAll();

    BlogGeneralDto blogFindById(Long id);
}
