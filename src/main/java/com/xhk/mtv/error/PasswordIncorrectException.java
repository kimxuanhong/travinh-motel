package com.xhk.mtv.error;

import org.springframework.security.core.AuthenticationException;

public class PasswordIncorrectException extends AuthenticationException {
    public PasswordIncorrectException(String msg, Throwable t) {
        super(msg, t);
    }

    public PasswordIncorrectException(String msg) {
        super(msg);
    }
}
