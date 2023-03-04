package org.usman.blog_spring_boot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.usman.blog_spring_boot.utility.Cat;

@Repository
public interface CatRepository  extends JpaRepository<Cat,Long> {

//    searching in cat
    public  Cat findCatByCategory(String name);
}
