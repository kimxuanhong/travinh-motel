package com.xhk.mtv.error;

public enum ApiErrorType {
    NO_DATA(1),
    LOGIN_ERROR(2),
    SERVER_ERROR(3),
    NOT_VERIFY_OTP(4),
    INVALID_FORMAT(5),
    INVALID_REQUEST(6),
    INVALID_AUTHORITY(7),
    DUPLICATION_ERROR(8),
    RESOURCE_NOT_FOUND(9),
    BUSINESS_LOGIC_ERROR(10);

    /** The status cd. */
    public final int code;

    /**
     * Instantiates a new status cd.
     *
     * @param code the status cd
     */
    ApiErrorType(int code) {
        this.code = code;
    }
}
