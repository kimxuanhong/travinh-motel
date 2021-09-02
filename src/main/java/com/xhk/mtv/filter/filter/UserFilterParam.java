package com.xhk.mtv.filter.filter;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFilterParam extends FilterParam {
    private String email;
}
