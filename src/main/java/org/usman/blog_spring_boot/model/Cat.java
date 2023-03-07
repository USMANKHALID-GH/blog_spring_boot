package org.usman.blog_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Valid
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cat_id")
    private Long id;
    @NotBlank(message="category must not be null1")
    @Column(unique = true, nullable = false)
    private String category;


//    @OneToMany(mappedBy = "cat")
//    private List<Blog> blog;
}
