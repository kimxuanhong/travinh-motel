package com.xhk.mtv.entity;

import com.xhk.mtv.entity.enums.PermissionName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@Entity
public class StaffPermission {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private PermissionName name;
}
