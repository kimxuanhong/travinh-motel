package com.xhk.mtv.service;

import com.xhk.mtv.filter.QueryPredicate;
import com.xhk.mtv.payload.request.CreateAccountRequest;
import com.xhk.mtv.payload.request.RestRequest;
import com.xhk.mtv.payload.response.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {
    Page<UserResponse> getUsers(QueryPredicate predicate);

    UserResponse getUser(long id);

    boolean createUser(RestRequest<CreateAccountRequest> request);
}
