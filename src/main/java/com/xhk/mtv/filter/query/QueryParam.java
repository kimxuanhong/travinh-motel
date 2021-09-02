package com.xhk.mtv.filter.query;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.xhk.mtv.filter.QueryPredicate;
import com.xhk.mtv.filter.filter.FilterParam;
import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@Data
public abstract class QueryParam<T extends FilterParam> {
    @Min(0)
    private int size = Integer.MAX_VALUE;

    @Min(0)
    private int index;

    private String sort;

    private Sort.Direction order = Sort.DEFAULT_DIRECTION;

    private T filter;

    protected abstract Predicate buildFilterPredicate(String timezone);

    protected abstract OrderSpecifier<?> buildOrderClause();

    public final QueryPredicate build(String timezone) {
        Predicate predicate = this.buildFilterPredicate(timezone);
        OrderSpecifier<?> order = this.buildOrderClause();
        return new QueryPredicate(order, predicate, this.getIndex(), this.getSize());
    }

    public final QueryPredicate build() {
         return build(null);
    }
}
