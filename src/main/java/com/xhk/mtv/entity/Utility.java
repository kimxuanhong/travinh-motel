package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Utility {
    @Id
    private Long id;

    private String name;
}
