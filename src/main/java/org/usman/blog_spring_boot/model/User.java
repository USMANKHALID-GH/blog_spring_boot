package org.usman.blog_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;


@Data
@Entity
public class User extends AbstractModel {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "user_id")
    private Long id;
    private String userName;
    @Column(name = "user_password")
    private String password;
    @Email
    private String userEmail;



}
