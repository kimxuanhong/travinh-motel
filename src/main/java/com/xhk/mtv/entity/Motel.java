package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Motel extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String thumbnail;

    private BigDecimal price;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne
    private Staff createBy;

    @ManyToOne
    private Staff updateBy;
}
