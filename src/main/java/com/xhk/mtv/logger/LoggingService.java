package com.xhk.mtv.logger;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;


@Component
public class LoggingService {

    private static final Logger logger = LogManager.getLogger(LoggingService.class);

    private final Gson gson = (new GsonBuilder()).create();

    public void logRequest(HttpServletRequest httpServletRequest, Object body) {
        List<LogHeader> headers = buildHeadersMap(httpServletRequest);
        LogHttpRequestEntity entity = new LogHttpRequestEntity();
        entity.setType("http-request");
        entity.setMethod(httpServletRequest.getMethod());
        entity.setHeaders(headers);
        entity.setPath(httpServletRequest.getRequestURI());
        entity.setBody(body);

        logger.info("{}", gson.toJson( new LogMessage("Request", entity)));
    }

    public void logResponse(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object body) {
        List<LogHeader> headers = buildHeadersMap(httpServletResponse);
        LogHttpResponseEntity entity = new LogHttpResponseEntity();
        entity.setType("http-response");
        entity.setMethod(httpServletRequest.getMethod());
        entity.setHeaders(headers);
        entity.setPath(httpServletRequest.getRequestURI());
        entity.setBody(body);

        logger.info("{}", gson.toJson( new LogMessage("Response", entity)));
    }

    private List<LogHeader> buildHeadersMap(HttpServletRequest request) {
        List<LogHeader> list = new ArrayList<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            list.add(new LogHeader(key, value));
        }
        return list;
    }

    private List<LogHeader> buildHeadersMap(HttpServletResponse response) {
        List<LogHeader> list = new ArrayList<>();
        Collection<String> headerNames = response.getHeaderNames();
        for (String header : headerNames) {
            list.add(new LogHeader(header, response.getHeader(header)));
        }
        return list;
    }
}