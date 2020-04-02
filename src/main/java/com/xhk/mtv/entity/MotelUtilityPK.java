package com.xhk.mtv.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Data
@Embeddable
public class MotelUtilityPK implements Serializable {

    @Column(name = "motel_id ")
    private long motelId;

    @Column(name = "utility_id")
    private long utilityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotelUtilityPK that = (MotelUtilityPK) o;
        return motelId == that.motelId &&
                utilityId == that.utilityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(motelId, utilityId);
    }

}
