package com.xhk.mtv.error.response;

import com.xhk.mtv.annotation.CustomResponse;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel
@CustomResponse
public class EmptyResponse {
    @NotNull
    private ResponseStatus status = ResponseStatus.SUCCESS;

    public static final EmptyResponse instance = new EmptyResponse();
}
