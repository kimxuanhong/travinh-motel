package com.xhk.mtv.error;

import static com.xhk.mtv.error.Message.RESOURCE_NOT_FOUND;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz, long id) {
        super(String.format(RESOURCE_NOT_FOUND, clazz.getSimpleName(), id));
    }

    public NotFoundException(Class<?> clazz, String id) {
        super(String.format(RESOURCE_NOT_FOUND, clazz.getSimpleName(), id));
    }

    public NotFoundException(Class<?> clazz, Object id) {
        super(String.format(RESOURCE_NOT_FOUND, clazz.getSimpleName(), id.toString()));
    }
}
