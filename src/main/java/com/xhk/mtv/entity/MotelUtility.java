package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MotelUtility extends AbstractEntity {
    @EmbeddedId
    private MotelUtilityPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motel_id ")
    @MapsId("motel_id ")
    private Motel motel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utility_id")
    @MapsId("utility_id")
    private Utility utility;
}
