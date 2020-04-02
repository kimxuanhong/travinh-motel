package com.xhk.mtv.entity;


import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class City {
    @Id
    private long id;

    private String type;

    private String name;
}
