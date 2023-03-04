package org.usman.blog_spring_boot.utility;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
