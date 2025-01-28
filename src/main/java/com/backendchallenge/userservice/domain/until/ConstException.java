package com.backendchallenge.userservice.domain.until;

public record ConstException() {
    public static final String ROLE_EMPTY = "Role is empty";
    public static final String USER_NAME_EMPTY = "User name is empty";
    public static final String USER_LAST_NAME_EMPTY = "User last name is empty";
    public static final String USER_DOCUMENT_EMPTY = "User document is empty";
    public static final String USER_PHONE_EMPTY = "User phone is empty";
    public static final String USER_BIRTHDATE_EMPTY = "User birthdate is empty";
    public static final String USER_EMAIL_EMPTY = "User email is empty";
    public static final String USER_PASSWORD_EMPTY = "User password is empty";
    public static final String USER_EMAIL_INVALID_FORMAT = "Invalid user email format";
    public static final String USER_PHONE_INVALID_FORMAT = "Invalid user phone number format";
    public static final String INVALID_USER_DOCUMENT_FORMAT = "Invalid user document format";
    public static final String USER_OLDER_THAT_THE_VALID_AGE = "User must be older than the valid age";
}
