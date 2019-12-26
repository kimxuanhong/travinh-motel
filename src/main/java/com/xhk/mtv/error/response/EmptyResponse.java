package com.xhk.mtv.error.response;

import com.xhk.mtv.annotation.CustomResponse;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@CustomResponse
public class EmptyResponse {
    @NotNull
    private ResponseStatus status = ResponseStatus.SUCCESS;

    public static final EmptyResponse instance = new EmptyResponse();
}
