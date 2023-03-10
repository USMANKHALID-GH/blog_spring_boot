package org.usman.blog_spring_boot.mapper;

import org.springframework.data.domain.Page;
import org.usman.blog_spring_boot.dto.BaseDto;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.model.AbstractModel;
import org.usman.blog_spring_boot.model.Blog;

import java.util.List;

public interface EntityMapper <D extends BaseDto , E extends AbstractModel> {

    E toEntity(D dto);
    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);
    List<D> toDto(List<E> entityList);



}
