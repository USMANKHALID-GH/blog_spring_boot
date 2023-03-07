package org.usman.blog_spring_boot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
public class Blog  extends AbstractModel{
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private  Long id;
    @NotNull(message="Title must be provided")
    private  String title;
    @NotNull(message="content must be provided")
    private String content;
    private String image;
//    @NotNull(message="date created must be provided")
//    private LocalDate dateCreated;

    @ManyToOne( fetch = FetchType.EAGER,optional = false)

    @JoinColumn(name = "cat_id", nullable = false)
    private Cat cat;
}
