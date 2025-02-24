package com.backendchallenge.userservice.domain.until;

import java.time.LocalDate;

public class ConstTest {
    public static final String ROLE_NAME_TEST = "RoleTest";
    public static final Long ID_TEST = 1L;

    public static final String EMAIL_VALID = "test@email.com";
    public static final String PASSWORD_VALID = "password123";
    public static final String DOCUMENT_VALID = "12345678";
    public static final String PHONE_VALID = "3244710909";
    public static final LocalDate BIRTHDATE_VALID = LocalDate.of(1990, 1, 1);
    public static final String BIRTHDATE_STRING_VALID = "01/01/1990";
    public static final String NAME_VALID = "John";
    public static final String LAST_NAME_VALID = "Doe";

    public static final String EMAIL_NEW = "newemail@email.com";
    public static final String PASSWORD_NEW = "newpassword";
    public static final String DOCUMENT_NEW = "87654321";
    public static final String PHONE_NEW = "555-4321";
    public static final LocalDate BIRTHDATE_NEW = LocalDate.of(1995, 5, 5);
    public static final String NAME_NEW = "Jane";
    public static final String LAST_NAME_NEW = "Smith";

    public static final String INVALID_PHONE_TEST ="0000000000000000000000000000";
    public static final String INVALID_DOCUMENT_TEST = "ASDFGHJKL";
    public static final String INVALID_EMAIL_TEST = "INVALID_EMAIL";
    public static final String EMPTY_STRING = "";

    public static final Long ROLE_ID_NEW = 2L;
    public static final String ROLE_NAME_NEW = "New Role";
    public static final String ENCODED_PASSWORD_TEST = "ENCODED";

    public static final String VALID_TOKEN = "valid.token.here";
    public static final String EXPIRED_TOKEN = "expired.token.here";
    public static final String UNSUPPORTED_TOKEN = "unsupported.token.here";
    public static final String MALFORMED_TOKEN = "malformed.token.here";
    public static final String EMPTY_TOKEN = " ";
    public static final String USER_EMAIL = "user@example.com";
    public static final String NOT_FOUND_EMAIL = "NotFound@test.com";

    public static final String FIELD1 = "field1";
    public static final String FIELD2 = "field2";
    public static final String ERROR1 = "error1";
    public static final String ERROR2 = "error2";
    public static final Integer AUTHORITIES_SIZE = 1;

    public static final Long USER_ID = 1L;
    public static final Long RESTAURANT_ID = 2L;


    private ConstTest() {
    }
}
