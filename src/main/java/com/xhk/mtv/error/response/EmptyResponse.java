package com.xhk.mtv.error.response;

import com.xhk.mtv.adapter.RestHolderService;
import com.xhk.mtv.common.Constants;
import com.xhk.mtv.payload.response.RestResponse;
import com.xhk.mtv.payload.response.RestResponseHeader;

import java.util.Date;

import static com.xhk.mtv.error.Status.SUCCESS_CODE;


public class EmptyResponse extends RestResponse<Object> {

    public static final EmptyResponse instance = new EmptyResponse();

    private EmptyResponse() {
        RestResponseHeader header = new RestResponseHeader();
        header.setRsCode(SUCCESS_CODE.code);
        header.setProcessTime(RestHolderService.getInstance().calculateProcessTime());
        header.setRsDate(new Date());
        header.setRsDesc(SUCCESS_CODE.message);
        header.setServiceId(Constants.SERVICE_ID);

        this.setHeader(header);
    }
}
