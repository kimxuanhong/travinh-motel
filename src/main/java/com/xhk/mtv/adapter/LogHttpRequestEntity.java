package com.xhk.mtv.adapter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogHttpRequestEntity {
	private String type;
	private String method;
	private String path;
	private List<LogHeader> headers;
	private Object body;
}