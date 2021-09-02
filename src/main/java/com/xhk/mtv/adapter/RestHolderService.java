package com.xhk.mtv.adapter;

import com.xhk.mtv.payload.request.RestRequest;
import com.xhk.mtv.payload.response.RestResponse;
import lombok.Data;

import java.util.Date;

@Data
public class RestHolderService {

    private RestHolderService() {
    }

    private static class SingletonHelper {
        private static final RestHolderService INSTANCE = new RestHolderService();
    }

    public static RestHolderService getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private String serviceId;
    private long startTime;
    private long processTime;
    private Date requestDate = new Date();
    private RestRequest<?> request;
    private RestResponse<?> response;

    public long calculateProcessTime() {
        if (processTime > 0) return processTime;

        long responseTime = System.currentTimeMillis() - startTime;
        processTime = responseTime / 1000000;
        return responseTime;
    }

}
