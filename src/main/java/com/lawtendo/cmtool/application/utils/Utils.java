package com.lawtendo.cmtool.application.utils;

import java.sql.Timestamp;
//import java.util.UUID;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public interface Utils {

	final static String SECRET_KEY = "Lawtend0@secret";
	final static String ISSUED_BY = "https://localhost:8080";
	final static String GOOGLE_CLIENT_ID = "838685128593-nf74rgro0366o6emvgmri83dh3j0cr45.apps.googleusercontent.com";
	
	final static Long EXPIRATION_DURATION = (long) 1000 * 60;		// 1 min duration
	
	final static String EMAIL = "email";
	final static String UUID = "uuid";
	final static String ROLE = "role";
	final static String EXPIRED_AT = "expiredAt";
	final static String CREATED_AT = "createdAt";
	
	final static String AUTH_DEFAULT = "default";
	final static String AUTH_GOOGLE = "google";
	
	final static String ROLE_PUBLIC = "public";
	final static String ROLE_GUEST = "guest";
	final static String ROLE_USER = "user";
	final static String ROLE_ADMIN = "admin";
	
	final static String HEADER_AUTH = "Authorization";
	
	
	public static Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static String generateHash(String string,String salt) {
		if(string != null && salt != null) {
			String hash = Jwts.builder().claim("password", string).signWith(SignatureAlgorithm.HS256, salt).compact();
			return hash;
		}
		return null;
	}
	
	public static String decodeHash(String hash, String salt) {
		if(hash != null && salt != null) {
			Jws<Claims> jws;
			
			try {
				jws = Jwts.parser().setSigningKey(salt).parseClaimsJws(hash);
				return (String) jws.getBody().get("password");
				
			}catch(ExpiredJwtException exception) {
				System.out.println("Token Expired !!!");
				System.out.println(exception);
				
			}catch(SignatureException exception) {
				System.out.println("Signature exception !!!");
				System.out.println(exception);
				
			}catch(JwtException exception) {
				System.out.println("Some other exception occured !!!");
				System.out.println(exception);
			}
		}
		
		return null;
	}
}
