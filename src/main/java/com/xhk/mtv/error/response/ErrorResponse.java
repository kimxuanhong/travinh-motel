package com.xhk.mtv.error.response;

import com.xhk.mtv.annotation.CustomResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CustomResponse
public class ErrorResponse<T> extends Response<T> {
    public ErrorResponse(T data) {
        this.setStatus(ResponseStatus.FAILED);
        this.setData(data);
    }
}
