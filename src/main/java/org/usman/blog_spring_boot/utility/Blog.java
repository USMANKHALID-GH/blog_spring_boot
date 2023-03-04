package org.usman.blog_spring_boot.utility;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private  Long id;
    @NotNull(message="Title must be provided")
    private  String title;
    @NotNull(message="content must be provided")
    private String content;
    private String image;
    @NotNull(message="date created must be provided")
    private LocalDate dateCreated;

    @ManyToOne( fetch = FetchType.EAGER)
    private Cat cat;
}
