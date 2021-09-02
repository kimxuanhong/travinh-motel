package com.xhk.mtv.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.xhk.mtv.entity.QAccount;
import com.xhk.mtv.filter.QueryPredicate;
import com.xhk.mtv.payload.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CustomAccountRepository {

    @Autowired
    private EntityManager em;

    public UserResponse findUserById(Long id){
        QAccount account = QAccount.account;
        JPAQuery<UserResponse> query = userQuery().where(account.id.eq(id));
        return query.fetchOne();
    }

    public Page<UserResponse> findAllUserAsPageable(QueryPredicate predicate){
        JPAQuery<UserResponse> query = userQuery();
        return predicate.fetch(query);
    }

    private JPAQuery<UserResponse> userQuery() {
        QAccount account = QAccount.account;

        QBean<UserResponse> userRes = Projections.bean(
                UserResponse.class,
                account.id,
                account.firstName,
                account.lastName,
                account.phoneNumber,
                account.email
        );

        return new JPAQuery<>(em)
                .select(userRes)
                .from(account);
    }
}
