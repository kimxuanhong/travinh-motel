package com.xhk.mtv.adapter;


import com.xhk.mtv.common.Constants;
import com.xhk.mtv.logger.LoggingService;
import com.xhk.mtv.payload.response.RestResponse;
import com.xhk.mtv.payload.response.RestResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Date;

import static com.xhk.mtv.error.Status.SUCCESS_CODE;


@RestControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    @Autowired
    private LoggingService loggingService;

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter,
                            @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object response,
                                  @NonNull MethodParameter methodParameter,
                                  @NonNull MediaType mediaType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> aClass,
                                  @NonNull ServerHttpRequest serverHttpRequest,
                                  @NonNull ServerHttpResponse serverHttpResponse) {

        if (serverHttpRequest instanceof ServletServerHttpRequest
                && serverHttpResponse instanceof ServletServerHttpResponse) {

            loggingService.logResponse(
                    ((ServletServerHttpRequest) serverHttpRequest).getServletRequest(),
                    ((ServletServerHttpResponse) serverHttpResponse).getServletResponse(),
                    response
            );

            if (response instanceof RestResponse) {
                if (((RestResponse<?>) response).getHeader() == null) {
                    RestHolderService holderService = RestHolderService.getInstance();

                    RestResponseHeader header = new RestResponseHeader();
                    header.setRsCode(SUCCESS_CODE.code);
                    header.setProcessTime(holderService.calculateProcessTime());
                    header.setRsDate(new Date());
                    header.setServiceId(Constants.SERVICE_ID);
                    header.setRsDesc(SUCCESS_CODE.message);

                    ((RestResponse<?>) response).setHeader(header);

                }
            }
        }

        return response;
    }
}