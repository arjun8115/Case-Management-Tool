package com.lawtendo.cmtool.application.helper;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.lawtendo.cmtool.application.DAO.SessionDAO;
import com.lawtendo.cmtool.application.utils.Utils;
import com.lawtendo.cmtool.application.validations.UserValidator;


@Component
public class SessionHelper {
	
	
	
	Algorithm algorithm = Algorithm.HMAC256(Utils.SECRET_KEY);
	
	private static final Logger logger = LoggerFactory.getLogger(SessionHelper.class);
	
//	@Autowired
//	private Session session;
	
	//create session for default login
	public SessionDAO getSession(String email, String role, String uuid) {
		
		
		
		
		//token issued at
		Date createdAt  = Utils.getTimestamp();
		
		//token expired at
		Date expiredAt = new Date(createdAt.getTime() + 10*Utils.EXPIRATION_DURATION);
		
		String token = generateToken(uuid, email, role, createdAt, expiredAt);
		
		SessionDAO session = getAuthSession(uuid, email, role, token,
							new Timestamp(expiredAt.getTime()), new Timestamp(createdAt.getTime()));
				
		return session;
	}
	
	public SessionDAO getAuthSession(String uuid, String email, String role, String token,Timestamp expiredAt, Timestamp createdAt) {
		
		SessionDAO session = new SessionDAO();
		 session.setUuid(uuid);
		 session.setEmail(email);
		 session.setRole(role);
		 session.setToken(token);
		 session.setExpiredAt(expiredAt);
		 session.setCreatedAt(createdAt);
		 
		return session;
	}
	
	private String generateToken(String uuid, String email, String role, Date createdAt, Date expiredAt) {
		
		
		if(UserValidator.validateUuid(uuid) || 
				UserValidator.validateEmail(email) || UserValidator.validateRole(role)) {
			
			//creating claim Obj
			Map<String, Object> claimObj = new HashMap<>();
			claimObj.put(Utils.UUID, uuid);
			claimObj.put(Utils.EMAIL, email);
			claimObj.put(Utils.ROLE, role);
			
			String token = JWT.create().withIssuer(Utils.ISSUED_BY)
								.withIssuedAt(createdAt)
								.withExpiresAt(expiredAt)
								.withClaim(Utils.UUID, uuid)
								.withClaim(Utils.EMAIL, email)
								.withClaim(Utils.ROLE, role)
								.sign(algorithm);
						
			return token;
		}
		
		return null;
		
	}
	
	
	public String verifyToken(String token) {
		
		try {
			
			JWTVerifier verifier = JWT.require(algorithm).acceptExpiresAt(10).build();
			
			DecodedJWT jwt = verifier.verify(token);
			
			return jwt.getToken();
				
			
		}catch(JWTVerificationException exception) {
			logger.error("Some other exception occured !!!");
			System.out.println(exception);
		}
		
		return null;
		
	}
	
	public Map<String, Object> decodeToken(String token) {
		
		try {
			DecodedJWT jwt = JWT.decode(token);
			Map<String, Object> map = new HashMap<>();
			map.put(Utils.UUID, jwt.getClaim(Utils.UUID).asString());
			map.put(Utils.EMAIL, jwt.getClaim(Utils.EMAIL).asString());
			map.put(Utils.ROLE, jwt.getClaim(Utils.ROLE).asString());
			
			return map;
			
		}catch(JWTDecodeException e) {
			System.out.println(e);
		}
		
		return null;
	}

	public GoogleIdToken verifyGoogleToken(String idTokenString) {
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
										.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance())
										.setIssuer("accounts.google.com")
										.setAudience(Collections.singletonList(Utils.GOOGLE_CLIENT_ID)).build();
		
		try {
			
			GoogleIdToken idToken = verifier.verify(idTokenString);
			logger.info("google token verified");
			
				return idToken;

		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			logger.info("IOException Exception");
			e.printStackTrace();
		}
		catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			logger.info("Goggle Security Exception");
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Map<String, Object> decodeGoogleToken(GoogleIdToken idToken) {
		Payload payload = idToken.getPayload();
		Map<String,  Object> payloadMap = new HashMap<>();

		payloadMap.put(Utils.EMAIL, payload.getEmail());

		logger.info("token decoded");
		payloadMap.put(Utils.CREATED_AT, new Timestamp(payload.getIssuedAtTimeSeconds()));
		payloadMap.put(Utils.EXPIRED_AT, new Timestamp(payload.getExpirationTimeSeconds()));
		
		return payloadMap;
	}
	
	
}
