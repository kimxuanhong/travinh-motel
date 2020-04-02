package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Feedback extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "motel_id")
    private Motel motel;

    @ManyToOne
    private Customer createBy;
}
