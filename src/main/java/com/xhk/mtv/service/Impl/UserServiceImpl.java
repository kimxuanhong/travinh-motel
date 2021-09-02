package com.xhk.mtv.service.Impl;

import com.xhk.mtv.entity.Account;
import com.xhk.mtv.error.NotFoundException;
import com.xhk.mtv.filter.QueryPredicate;
import com.xhk.mtv.payload.request.CreateAccountRequest;
import com.xhk.mtv.payload.request.RestRequest;
import com.xhk.mtv.payload.response.UserResponse;
import com.xhk.mtv.repository.CustomAccountRepository;
import com.xhk.mtv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private CustomAccountRepository customAccountRepository;

    @Override
    public Page<UserResponse> getUsers(QueryPredicate predicate) {
        return customAccountRepository.findAllUserAsPageable(predicate);
    }

    @Override
    public UserResponse getUser(long id) {
        UserResponse response = customAccountRepository.findUserById(id);
        if (response == null) {
            throw new NotFoundException(Account.class, id);
        }

        return response;
    }

    @Override
    public boolean createUser(RestRequest<CreateAccountRequest> request) {

        return false;
    }
}
