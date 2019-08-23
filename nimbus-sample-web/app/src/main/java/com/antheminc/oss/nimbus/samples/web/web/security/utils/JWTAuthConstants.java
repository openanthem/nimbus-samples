package com.antheminc.oss.nimbus.samples.web.web.security.utils;

public class JWTAuthConstants {

	// TODO - externalize to application.yml
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 600000; // 10 min
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

}