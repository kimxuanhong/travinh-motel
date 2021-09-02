package com.xhk.mtv.error.response;

import com.xhk.mtv.payload.response.RestResponse;

public class ResponseUtils {

    public static <T> RestResponse<T> build(T body) {
        return new RestResponse<>(body);
    }
}
