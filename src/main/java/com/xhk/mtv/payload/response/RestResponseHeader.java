package com.xhk.mtv.payload.response;

import lombok.Data;

import java.util.Date;

@Data
public class RestResponseHeader {
    private String serviceId;

    private Date rsDate;
    private long processTime;
    private String rsCode;
    private String rsDesc;
    private Object details;
}
