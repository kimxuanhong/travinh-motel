package com.xhk.mtv.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@Where(clause = "delete_flag = false")
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic(optional = false)
    private boolean deleteFlag;

    /**
     * The created at.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    /**
     * The updated at.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    /**
     * Check version of entity
     */
    @Version
    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    private long version = 0L;

    /**
     * Pre persist.
     */
    @PrePersist
    public void prePersist() {
        Date date = new Date();
        createdAt = date;
        updatedAt = date;
    }

    /**
     * On persist.
     */
    @PreUpdate
    public void preUpdate() {
        Date date = new Date();
        updatedAt = date;
    }
}


