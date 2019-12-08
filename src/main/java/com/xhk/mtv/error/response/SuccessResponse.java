package com.xhk.mtv.error.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status", "data"})
public class SuccessResponse<T> extends Response<T> {
    public SuccessResponse(T data) {
        this.setData(data);
        this.setStatus(ResponseStatus.SUCCESS);
    }
}

