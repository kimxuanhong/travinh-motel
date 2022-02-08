package com.xhk.mtv.error.response;

import com.xhk.mtv.adapter.RestHolderService;
import com.xhk.mtv.common.Constants;
import com.xhk.mtv.error.ResponseStatus;
import com.xhk.mtv.payload.response.RestResponse;
import com.xhk.mtv.payload.response.RestResponseHeader;

import java.util.Date;

public class ErrorResponse extends RestResponse<Object> {

    public static ErrorResponse build(ResponseStatus status, String messageDesc) {
        RestResponseHeader header = new RestResponseHeader();
        header.setRsCode(status.code);
        header.setRsDesc(status.message);
        header.setProcessTime(RestHolderService.getInstance().calculateProcessTime());
        header.setRsDate(new Date());
        header.setServiceId(Constants.SERVICE_ID);
        header.setDetails(messageDesc);

        ErrorResponse response = new ErrorResponse();
        response.setHeader(header);
        return response;
    }

    public static ErrorResponse build(ResponseStatus status, Object details) {
        RestResponseHeader header = new RestResponseHeader();
        header.setRsCode(status.code);
        header.setRsDesc(status.message);
        header.setProcessTime(RestHolderService.getInstance().calculateProcessTime());
        header.setRsDate(new Date());
        header.setServiceId(Constants.SERVICE_ID);
        header.setDetails(details);

        ErrorResponse response = new ErrorResponse();
        response.setHeader(header);
        return response;
    }
}
