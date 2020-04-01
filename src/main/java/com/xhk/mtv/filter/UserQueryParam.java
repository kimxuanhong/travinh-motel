package com.xhk.mtv.filter;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import org.springframework.data.domain.Sort;

public class UserQueryParam extends QueryParam<FilterParam> {
    @Override
    protected Predicate buildFilterPredicate(String timezone) {
        BooleanBuilder builder = new BooleanBuilder();
        return builder;
    }

    @Override
    protected OrderSpecifier<?> buildOrderClause() {
        String orderField = getSort();
        if(orderField == null) {
            orderField = "id";
        }

        ComparableExpressionBase<? extends Comparable> orderExpr = null;
        OrderSpecifier<?> orderSpecifier = getOrder() == Sort.Direction.ASC ? orderExpr.asc() : orderExpr.desc();
        return orderSpecifier;
    }
}
