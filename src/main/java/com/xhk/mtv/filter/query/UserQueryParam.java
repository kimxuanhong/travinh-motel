package com.xhk.mtv.filter.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.xhk.mtv.entity.QAccount;
import com.xhk.mtv.filter.filter.UserFilterParam;
import org.springframework.data.domain.Sort;

public class UserQueryParam extends QueryParam<UserFilterParam> {
    @Override
    protected Predicate buildFilterPredicate(String timezone) {
        BooleanBuilder builder = new BooleanBuilder();
        UserFilterParam filter = this.getFilter();
        QAccount account = QAccount.account;
        if(filter == null) { return builder; }

        if(!filter.getIds().isEmpty()) { builder.and(account.id.in(filter.getIds())); }

        if(filter.getEmail()!=null && !filter.getEmail().isEmpty()){
            builder.and(account.email.containsIgnoreCase(filter.getEmail()));
        }

        return builder;
    }

    @Override
    protected OrderSpecifier<?> buildOrderClause() {
        String orderField = this.getSort();
        if(orderField == null) {
            orderField = "id";
        }

        QAccount account = QAccount.account;
        ComparableExpressionBase<?> orderExpr = null;
        switch (orderField) {
            default:
                orderExpr = account.id;
                break;
        }

        return this.getOrder() == Sort.Direction.ASC ? orderExpr.asc() : orderExpr.desc();
    }
}
