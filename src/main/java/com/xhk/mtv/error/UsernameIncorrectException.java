package com.xhk.mtv.error;

import org.springframework.security.core.AuthenticationException;

public class UsernameIncorrectException extends AuthenticationException {
    public UsernameIncorrectException(String msg, Throwable t) {
        super(msg, t);
    }

    public UsernameIncorrectException(String msg) {
        super(msg);
    }
}
