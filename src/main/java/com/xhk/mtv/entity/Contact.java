package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Contact extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String content;

    private String reply;

    @ManyToOne
    @JoinColumn(name = "motel_id")
    private Motel motel;

    @ManyToOne
    private Customer createBy;

    @ManyToOne
    private Staff updateBy;
}
