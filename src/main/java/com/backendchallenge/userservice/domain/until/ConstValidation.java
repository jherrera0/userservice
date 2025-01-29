package com.backendchallenge.userservice.domain.until;

public class ConstValidation {
    public static final String  PHONE_REGEX = "^(\\+\\d{1,2})?(\\d{0,10})$";
    public static final String  EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String DOCUMENT_REGEX = "\\d+";
    public static final int MIN_AGE = 18;

    private ConstValidation() {
    }
}
