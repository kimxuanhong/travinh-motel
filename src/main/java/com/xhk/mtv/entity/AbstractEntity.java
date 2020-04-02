package com.xhk.mtv.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;

import static java.time.OffsetDateTime.now;

@Getter
@Setter
@MappedSuperclass
@Where(clause = "delete_flag = false")
public class AbstractEntity {

    @Basic(optional = false)
    private boolean deleteFlag;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    @PrePersist
    public void onPersist() {
        updatedAt = createdAt = now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = now();
    }
}


