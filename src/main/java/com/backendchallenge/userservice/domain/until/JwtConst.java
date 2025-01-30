package com.backendchallenge.userservice.domain.until;

public class JwtConst {


    public static final Long EXPIRATION_TIME = 30*60*1000L;
    public static final String BEARER = "Bearer";
    public static final String SPLITERSTRING = " ";
    public static final String HEADER_STRING = "Authorization";
    public static final String ID = "Id";
    public static final String ROLE = "Role";


    private JwtConst() {
    }
}
