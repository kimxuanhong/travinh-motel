package com.xhk.mtv.error.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;

@Getter
@Setter
@JsonPropertyOrder({"status", "data"})
public class Response<T> {
    @NotNull
    private ResponseStatus status;

    @JsonInclude(Include.NON_NULL)
    private T data;

    public void printResponse(HttpServletResponse res) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(this);
        res.setContentType("application/json;charset=UTF-8");
        res.setCharacterEncoding("UTF-8");

        PrintWriter writer = res.getWriter();
        writer.print(json);
        writer.flush();
    }
}
