package com.xhk.mtv.service.Impl;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import com.xhk.mtv.filter.QueryPredicate;
import com.xhk.mtv.entity.QAccount;
import com.xhk.mtv.response.UserRes;
import com.xhk.mtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EntityManager em;

    @Override
    public Page<UserRes> getUsers(QueryPredicate predicate) {
        JPAQuery<UserRes> query = userQuery();
        return predicate.fetch(query);
    }

    @Override
    public UserRes getUser(long id) {
        QAccount account = QAccount.account;
        JPAQuery<UserRes> query = userQuery().where(account.id.eq(id));
        return query.fetchOne();
    }

    private JPAQuery<UserRes> userQuery() {
        QAccount account = QAccount.account;

        QBean<UserRes> userRes = Projections.bean(
                UserRes.class,
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
