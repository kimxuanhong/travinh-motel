package com.xhk.mtv.error;

public enum ErrorMessage {
    //Authentication error
    LOGIN_FAILED("The Phone number or password incorrect.", 1),
    PHONE_NUMBER_DOES_NOT_EXIST("Phone number not found.", 1),
    PHONE_NUMBER_OR_PASSWORD_NOT_NULL("Phone or password is empty.", 2),
    INVALID_AUTHORIZATION("Invalid Authorization.", 3),
    ACCOUNT_NOT_VERIFY_OTP("Account is not verify OTP.", 4),

    //System error
    INTERNAl_ERROR("Internal server error.", 5),
    METHOD_NOT_SUPPORT("Request method is not support.", 6),
    INVALID_REQUEST_URL("Request url is invalid.", 6),
    CAN_NOT_CONVERT_DATE("Date invalid format.", 6),

    //validate field error
    MISSING_PHONE_NUMBER("PhoneNumber is null or empty.", 6),
    MISSING_PASSWORD("Password is null or empty.", 6),
    MISSING_FIRST_NAME("FirstName is null or empty.", 6),
    MISSING_LAST_NAME("LastName is null or empty.", 6),
    MISSING_EMAIL_ADDRESS("Email is null or empty.", 6),
    INVALID_EMAIL_ADDRESS("Email invalid format.", 6),
    INVALID_LENGTH_OTP_NUMBER("Length of OTP invalid.", 6),
    MISSING_OTP_NUMBER("OTP is null or empty.", 6),
    MISSING_TOKEN("Token is null or empty.", 6),
    MISSING_DOB("DOB is null or empty.", 6),
    MISSING_REFERENCE_TYPE("Reference type is null or empty.", 6),
    MISSING_USER_ID("user_id is null or empty.", 6),
    INVALID_BASE64_STRING("Invalid base64 string.", 6),

    OLD_VERSION_OBJECT("Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect).", 6);

    public final String desc;
    public final int code;

    ErrorMessage(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }
}
