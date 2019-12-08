package com.xhk.mtv.error.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@JsonPropertyOrder({"status", "data"})
public class Response<T> {
    @NotNull
    private ResponseStatus status;

    @JsonInclude(Include.NON_NULL)
    private T data;
}
