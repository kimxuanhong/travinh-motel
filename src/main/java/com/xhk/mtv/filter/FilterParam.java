package com.xhk.mtv.filter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class FilterParam {
    private List<Long> ids = new ArrayList<>();
}
