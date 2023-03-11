package org.usman.blog_spring_boot.mapper;

import org.mapstruct.Mapper;
import org.usman.blog_spring_boot.dto.BlogDto;
import org.usman.blog_spring_boot.dto.UserDto;
import org.usman.blog_spring_boot.model.Blog;
import org.usman.blog_spring_boot.model.User;
@Mapper(componentModel = "spring")
public interface UserMapper  extends  EntityMapper<UserDto, User>{

    UserDto toDto( User user);
    User toEntity(UserDto userDto);
}
