package com.xhk.mtv.error.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"status", "data"})
public class ErrorResponse<T> extends Response<T> {
    public ErrorResponse(T data) {
        this.setStatus(ResponseStatus.FAILED);
        this.setData(data);
    }
}
