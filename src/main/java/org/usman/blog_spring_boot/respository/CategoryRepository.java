package org.usman.blog_spring_boot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.blog_spring_boot.model.Cat;

@Repository
public interface CategoryRepository extends JpaRepository<Cat,Long> {


Cat findCatByCategory(String name);
}
