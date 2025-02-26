package com.backendchallenge.userservice.domain.until;

public class ConstDocumentation {
    public static final String USER_REST_NAME   = "User Rest API";
    public static final String USER_REST_DESCRIPTION = "API to manage user authentication and user creation requests";
    public static final String CREATE_OWNER_OPERATION = "Create a new owner user";

    public static final String CREATE_OWNER_CODE_201 = "Owner user created successfully";
    public static final String CREATE_OWNER_CODE_400 = "Invalid data to request create owner user";
    public static final String FIND_OWNER_OPERATION = "Find a owner user by id";
    public static final String FIND_OWNER_CODE_201 = "Owner found process did successfully";
    public static final String AUTH_TAG_NAME = "Auth API";
    public static final String AUTH_TAG_DESCRIPTION = "API to manage user authentication";
    public static final String AUTH_LOGIN_SUMMARY = "Login user";
    public static final String AUTH_LOGIN_DESCRIPTION = "Login user with email and password";

    public static final String CODE_201 = "201";
    public static final String CODE_400 = "400";
    public static final String CODE_403 = "403";
    public static final int CODE_404_INT = 404;
    public static final int CODE_400_INT = 400;
    public static final int CODE_302_INT = 302;


    public static final String AUTH_LOGIN_DESCRIPTION_201 = "User logged in successfully";
    public static final String AUTH_LOGIN_DESCRIPTION_403 = "Invalid credentials";
    public static final String CREATE_EMPLOYEE_OPERATION = "Create a new employee user";
    public static final String CREATE_EMPLOYEE_CODE_201 = "Employee user created successfully";
    public static final String CREATE_EMPLOYEE_CODE_400 = "Invalid data to request create employee user";
    public static final String CREATE_EMPLOYEE_CODE_403 = "User not authorized to create employee user";
    public static final String CREATE_OWNER_CODE_403 = "User not authorized to create owner user";
    public static final String CREATE_CLIENT_OPERATION = "Create a new client user";
    public static final String CREATE_CLIENT_CODE_201 = "Client user created successfully";
    public static final String CREATE_CLIENT_CODE_400 = "Invalid data to request create client user";
    public static final String CREATE_CLIENT_CODE_403 = "User not authorized to create client user";
    public static final String GET_USER_PHONE_OPERATION = "Get user phone";
    public static final String GET_USER_PHONE_CODE_201 = "User phone found process did successfully";
    public static final String GET_USER_PHONE_CODE_400 = "Invalid data to request get user phone";
    public static final String GET_USER_PHONE_CODE_403 = "User not authorized to get user phone";


    private ConstDocumentation() {
    }
}
