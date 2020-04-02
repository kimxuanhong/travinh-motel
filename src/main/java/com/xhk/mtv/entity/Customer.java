package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Customer extends AbstractEntity {
    @Id
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private Account account;

    private String identity;
}
