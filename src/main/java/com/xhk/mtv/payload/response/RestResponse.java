package com.xhk.mtv.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class RestResponse<RestResponseBody> {

    private RestResponseHeader header;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RestResponseBody body;

    public RestResponse() {
    }

    public RestResponse(RestResponseBody body) {
        this.body = body;
    }
}
