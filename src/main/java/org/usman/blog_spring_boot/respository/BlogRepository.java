package org.usman.blog_spring_boot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.usman.blog_spring_boot.utility.Blog;


import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository  extends JpaRepository<Blog,Long> {



    @Query("SELECT blog FROM Blog  blog WHERE blog.content LIKE '%:name%'")
    Optional<List<Blog>>  findBySearchParameter(@Param("name") String name);
}
