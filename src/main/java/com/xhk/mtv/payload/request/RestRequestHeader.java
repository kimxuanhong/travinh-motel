package com.xhk.mtv.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RestRequestHeader {
    @NotBlank
    private String rqAppId;
    @NotBlank
    private String rqDate;
    private String rqMethod;
    private String rqUser;
    private String rqPass;
}
