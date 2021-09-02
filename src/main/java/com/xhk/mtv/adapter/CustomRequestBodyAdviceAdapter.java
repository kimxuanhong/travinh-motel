package com.xhk.mtv.adapter;

import com.xhk.mtv.logger.LoggingService;
import com.xhk.mtv.payload.request.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;


@RestControllerAdvice
public class CustomRequestBodyAdviceAdapter extends RequestBodyAdviceAdapter {

    @Autowired
    LoggingService loggingService;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Override
    public boolean supports(@NonNull MethodParameter methodParameter,
                            @NonNull Type type,
                            @NonNull Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    @NonNull
    public Object afterBodyRead(@NonNull Object body,
                                @NonNull HttpInputMessage inputMessage,
                                @NonNull MethodParameter parameter,
                                @NonNull Type targetType,
                                @NonNull Class<? extends HttpMessageConverter<?>> converterType) {

        loggingService.logRequest(httpServletRequest, body);
        if (body instanceof RestRequest) {
            RestRequest<?> request = (RestRequest<?>) body;
            RestHolderService.getInstance().setRequest(request);
            RestHolderService.getInstance().setStartTime(System.currentTimeMillis());
        }

        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }
}
