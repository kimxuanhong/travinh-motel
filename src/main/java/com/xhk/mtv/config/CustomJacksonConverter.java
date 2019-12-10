package com.xhk.mtv.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.xhk.mtv.annotation.CustomResponse;
import com.xhk.mtv.error.response.ApiErrorDetails;
import com.xhk.mtv.error.response.ErrorResponse;
import com.xhk.mtv.error.response.SuccessResponse;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

@Component
public class CustomJacksonConverter extends MappingJackson2HttpMessageConverter {
    public CustomJacksonConverter(Jackson2ObjectMapperBuilder builder) {
        super(builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE).build());
    }

    @Override
    public boolean canRead(Type type, @Nullable Class<?> contextClass, @Nullable MediaType mediaType) {
        if(isCustomResponse(contextClass)) {
            return super.canRead(type, contextClass, mediaType);
        }
        return false;
    }

    @Override
    public boolean canWrite(Type type, Class<?> clazz, @Nullable MediaType mediaType) {
        if(isApplicableToWrite(type, clazz)) {
            return super.canWrite(type, clazz, mediaType);
        }
        return false;
    }

    /**
     * If the response object itself is annotated with CustomResponse
     * then return true, otherwise check the type parameter.
     *
     */
    private boolean isApplicableToWrite(Type type, Class<?> clazz) {
        if(isCustomResponse(clazz)) {
            return true;
        }
        return isTypeApplicable(type);
    }

    /**
     * If type is a Class object and itself is annotated with CustomResponse
     * then return true, else
     *
     * If type is a generic type and itself is annotated with CustomResponse
     * then return true, else
     *
     * If type is a generic type then check if all its type arguments are
     * type applicable (using recursive).
     */
    private boolean isTypeApplicable(Type type) {
        if(type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            if(rawType instanceof Class) {
                if(isCustomResponse((Class) rawType)) {
                    return true;
                }
            }
            return Arrays
                    .stream(parameterizedType.getActualTypeArguments())
                    .anyMatch(this::isTypeApplicable);

        }
        if(type instanceof Class) {
            if(isCustomResponse((Class) type)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCustomResponse(Class clazz) {
        return clazz.isAnnotationPresent(CustomResponse.class);
    }

    @Override
    protected void writeInternal(Object object, @Nullable Type type, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
            if(object instanceof ApiErrorDetails){
                super.writeInternal(new ErrorResponse<>(object), type, outputMessage);
            }else {
                super.writeInternal(new SuccessResponse<>(object), type, outputMessage);
            }
    }
}
