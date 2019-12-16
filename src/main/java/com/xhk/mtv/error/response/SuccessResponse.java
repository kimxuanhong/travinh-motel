package com.xhk.mtv.error.response;

import com.xhk.mtv.annotation.CustomResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CustomResponse
public class SuccessResponse<T> extends Response<T> {
    public SuccessResponse(T data) {
        this.setData(data);
        this.setStatus(ResponseStatus.SUCCESS);
    }
}

