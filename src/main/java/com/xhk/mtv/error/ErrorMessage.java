package com.xhk.mtv.error;

public enum ErrorMessage {
    //Authentication error
    LOGIN_FAILED("The Phone number or password incorrect."),
    PHONE_NUMBER_NOT_FOUND("Phone number not found."),
    USER_NAME_NOT_FOUND("User name not found."),
    EMAIL_NOT_FOUND("Email not found."),
    PASSWORD_INCORRECT("Password incorrect."),
    INVALID_AUTHORIZATION("Invalid Authorization."),
    ACCOUNT_NOT_VERIFY_OTP("Account is not verify OTP."),

    //System error
    INTERNAl_ERROR("Internal server error."),
    METHOD_NOT_SUPPORT("Request method is not support."),
    INVALID_REQUEST_URL("Request url is invalid."),
    CAN_NOT_CONVERT_DATE("Date invalid format."),

    //validate field error
    MISSING_PHONE_NUMBER("PhoneNumber is null or empty."),
    MISSING_PASSWORD("Password is null or empty."),
    MISSING_FIRST_NAME("FirstName is null or empty."),
    MISSING_LAST_NAME("LastName is null or empty."),
    MISSING_EMAIL_ADDRESS("Email is null or empty."),
    INVALID_EMAIL_ADDRESS("Email invalid format."),
    INVALID_LENGTH_OTP_NUMBER("Length of OTP invalid."),
    MISSING_OTP_NUMBER("OTP is null or empty."),
    INVALID_TOKEN("Token invalid format."),
    MISSING_DOB("DOB is null or empty."),
    MISSING_REFERENCE_TYPE("Reference type is null or empty."),
    MISSING_USER_ID("user_id is null or empty."),
    INVALID_BASE64_STRING("Invalid base64 string."),

    OLD_VERSION_OBJECT("Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect).");

    public final String desc;

    ErrorMessage(String desc) {
        this.desc = desc;

    }
}
