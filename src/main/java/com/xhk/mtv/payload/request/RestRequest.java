package com.xhk.mtv.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class RestRequest<RestRequestBody> {
    @NonNull
    @Valid
    private RestRequestHeader header;

    @Valid
    private RestRequestBody body;
}
