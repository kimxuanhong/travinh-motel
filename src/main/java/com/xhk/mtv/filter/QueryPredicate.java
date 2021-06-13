package com.xhk.mtv.filter;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.*;

import java.util.List;

@Data
@AllArgsConstructor
public class QueryPredicate {
    private OrderSpecifier<?> order;
    private Predicate predicate;
    private int index;
    private int size;

    public final Sort asSpringSort() {
        Sort.Direction direction = order.isAscending() ? Sort.Direction.ASC : Sort.Direction.DESC;
        String property = order.getTarget().toString();
        return Sort.by(direction, property);
    }

    public <T> Page<T> fetch(JPAQuery<T> query) {
        int index = getIndex();
        int size = getSize();
        query.where(predicate).orderBy(order);
        long totalCount = query.fetchCount();
        List<T> result = query.offset((long) index * size).limit(size).fetch();
        Pageable pageable = PageRequest.of(index, size, asSpringSort());
        return new PageImpl<>(result, pageable, totalCount);
    }

    public <T> Page<T> wrapper(List<T> result) {
        long totalCount = result.size();
        int index = getIndex();
        int size = getSize();
        Pageable pageable = PageRequest.of(index, size, asSpringSort());
        return new PageImpl<>(result, pageable, totalCount);
    }
}
