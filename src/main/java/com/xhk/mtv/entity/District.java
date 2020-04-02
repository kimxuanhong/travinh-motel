package com.xhk.mtv.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class District {
    @Id
    private long id;

    private String type;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
