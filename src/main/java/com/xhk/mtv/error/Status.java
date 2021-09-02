package com.xhk.mtv.error;

public enum Status {
    SUCCESS_CODE("00", "Success!"),
    BUSINESS_LOGIC_ERROR("10", "Business logic exception."),
    INVALID_AUTHORIZATION("10", "Invalid Authorization."),
    INTERNAL_SERVER_ERROR("20", "Internal server error"),
    FORBIDDEN("10", "Access denied!"),
    METHOD_ARGUMENT_VALIDATION_FAILED("10", "Method argument validation failed"),
    METHOD_ARGUMENT_TYPE_MISMATCH("10", "Method argument type mismatch"),
    MISSING_REQUEST_PARAMETER("10", "Missing request parameter"),
    VALIDATION_EXCEPTION("10", "Validation exception"),
    RESOURCE_NOT_FOUND("10", "Resource not found.");

    Status(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public final String code;
    public final String message;
}
