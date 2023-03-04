package org.usman.blog_spring_boot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.blog_spring_boot.utility.Blog;

@Repository
public interface BlogRepository  extends JpaRepository<Blog,Long> {
}
