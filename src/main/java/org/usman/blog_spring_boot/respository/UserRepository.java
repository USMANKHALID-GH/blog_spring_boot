package org.usman.blog_spring_boot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.usman.blog_spring_boot.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
