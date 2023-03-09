package org.usman.blog_spring_boot.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.usman.blog_spring_boot.model.Blog;


import java.util.List;

@Repository
public interface BlogRepository  extends JpaRepository<Blog,Long> {


//searching in title
    List<Blog> findByTitleContaining(String string);

//searching in content
    List<Blog>  findByContentContaining(String string);


    List<Blog> findByTitleOrContentContaining(String string , String s);

    @Query(value = "FROM Blog blog where blog.cat.id=:id")
    List<Blog> findAllByCat(@Param("id") int id);


//    @Query(value = "FROM Blog blog where blog.cat.id=:id")
//    Page<Blog> findAllByCategory(@Param("id") int id);


    @Query(value = "FROM Blog blog where blog.cat.id=:id")
    Page<Blog>   findAll(Pageable pageable, @Param("id") int id);


}
