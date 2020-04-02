package com.xhk.mtv.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Ward {
    @Id
    private long id;

    private String type;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id")
    private District district;
}
