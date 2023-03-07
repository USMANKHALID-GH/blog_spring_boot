package org.usman.blog_spring_boot.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Slf4j
public abstract class AbstractModel implements Serializable {

    private static final long serialVersionUID=1L;

    @CreatedDate
    @NotNull(message = "date cant be null")
    @Column(name = "created_date",updatable = false
    ,columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    private LocalDateTime createdDate;

    @PrePersist
    private void perPersist(){

        this.createdDate=LocalDateTime.now();
        log.info(this.createdDate+".......................");
        }
}
