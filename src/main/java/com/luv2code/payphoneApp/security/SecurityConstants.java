package com.luv2code.payphoneApp.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL ="h2-console/**";
    public  static final long EXPIRATION_TIME = 3600_000; //1hr
    public  static final String SECRET = "SecretKeyToPayphone";
    public static final String  TOKEN_PREFIX ="Bearer ";
    public static final String HEADER_STRING ="Authorization"; //Authorization

}
