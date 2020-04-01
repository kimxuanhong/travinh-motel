package com.xhk.mtv.service;

import com.xhk.mtv.filter.QueryPredicate;
import com.xhk.mtv.response.UserRes;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserRes> getUsers(QueryPredicate predicate);

    UserRes getUser(long id);
}
