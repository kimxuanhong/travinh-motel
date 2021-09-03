package com.xhk.mtv.adapter;

import com.xhk.mtv.logger.LoggingService;
import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static io.swagger.models.HttpMethod.*;
import static org.springframework.boot.web.servlet.DispatcherType.*;

@Component
public class CustomLogInterceptor implements HandlerInterceptor {

    @Autowired
    private LoggingService loggingService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        if (REQUEST.name().equals(request.getDispatcherType().name()) && GET.name().equals(request.getMethod())) {
            loggingService.logRequest(request, request.getQueryString());
        }

        return true;
    }
}