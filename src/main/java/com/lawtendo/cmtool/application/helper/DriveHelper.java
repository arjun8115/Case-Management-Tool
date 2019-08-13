package com.lawtendo.cmtool.application.helper;




import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
//import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;


public class DriveHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(DriveHelper.class);
    
	@Autowired
	static
	DriveHelperMimeType driveHelperMimeType;
	
	private static final String APPLICATION_NAME = "Google Drive API Java Quickstart";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "tokens";
	
	private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);

	public static Credential createCredentialWithAccessTokenOnly(
		      HttpTransport transport, JsonFactory jsonFactory, TokenResponse tokenResponse) {
		    return new Credential(BearerToken.authorizationHeaderAccessMethod()).setFromTokenResponse(
		        tokenResponse);
		  }
	
	  public static Credential createCredentialWithRefreshToken(
		      HttpTransport transport, JsonFactory jsonFactory, TokenResponse tokenResponse) {
		    return new Credential.Builder(BearerToken.authorizationHeaderAccessMethod()).setTransport(
		        transport)
		        .setJsonFactory(jsonFactory)
		        .setTokenServerUrl(
		            new GenericUrl("https://server.example.com/token"))
		        .setClientAuthentication(new BasicAuthentication("s6BhdRkqt3", "7Fjfp0ZBr1KtDRbnfVdmIw"))
		        .build()
		        .setFromTokenResponse(tokenResponse);
		  }
	  
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		logger.info("load credential");
		java.io.File file = ResourceUtils.getFile("classpath:credentialsDrive.json");
        InputStream in = new FileInputStream(file);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        logger.info("link");
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
        		//.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        logger.info("port");
  
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}
	
	
//	private static Credential getCredentialstemp(final NetHttpTransport HTTP_TRANSPORT, TokenResponse tokenResponse) throws IOException {
//		logger.info("load credential");
//		java.io.File file = ResourceUtils.getFile("classpath:credentialsDrive.json");
//        InputStream in = new FileInputStream(file);
//        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
//        logger.info("link");
//     
//      
//      //  DataStoreFactory ds = new FileDataStoreFactory();
//        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
//        		.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
//        		
//                .setAccessType("offline")
//                .build();
//        
//        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
//        logger.info("port");
//  
//        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user").setFromTokenResponse(tokenResponse);
//        	
//        		//.setAccessToken("ya29.Gl1VB1cXaAt4IdxErzfpH3Jy57zdQRMzCKPvZ-OuVFsXRnUMesVisnC5sAlffg-00L-DWUlkP_itc0u8JeyIPhaQzrBYaYxUe65hOn8MEm-hZvWu34tM6sAGXxL3NOU");
//	}
	
	public static void driveAPI() throws GeneralSecurityException, IOException {
		 final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		 
		 
		 Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
	                .setApplicationName(APPLICATION_NAME)
	                .build();
		
		 logger.info("file list");
		 FileList result = service.files().list()
	                .setPageSize(10)
	                .setFields("nextPageToken, files(id, name)")
	                .execute();
		 List<File> files = result.getFiles();
		 if (files == null || files.isEmpty()) {
	            System.out.println("No files found.");
	        } else {
	            System.out.println("Files:");
	            for (File file : files) {
	                System.out.printf("%s (%s)\n", file.getName(), file.getId());
	            }
	        }
	}

	
	
	
	public static String driveAPI( MultipartFile newFile) throws GeneralSecurityException, IOException, FileNotFoundException {
		 final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		 
//		 Credential getCredential = getCredentials(HTTP_TRANSPORT);
//		 getCredential.getClientAuthentication();
		
		 
//		 String accessToken = getCredential.getAccessToken();
//			Long tokenExpireTime = getCredential.getExpirationTimeMilliseconds();
//			String refreshToken = getCredential.getRefreshToken();
//			
//			System.out.println("accessToken: "+accessToken);
			//TokenResponse tokenResponse = new TokenResponse();
			
//			tokenResponse.setAccessToken(accessToken);
//			tokenResponse.setRefreshToken(refreshToken);
//			tokenResponse.setExpiresInSeconds(tokenExpireTime*1000);
//		    tokenResponse.setTokenType("bearer");
//			//tokenResponse.setScope("create");
//		System.out.println(tokenResponse);
//			
		//String	accessToken = "ya29.GltYB-6tBRGR6JgDbNTv-CmFEB5Eg8j8My7je_PCoNLOZXYtLq0P3e3u3pMb6H9WrTOpB-BMrCBcHNFAs7VP7MNR1OYNXekDwAxKekQSInvPteTphjupzYhYs2-D\r\n";
		String a=	"ya29.GltYB2Eoc6cE83FQpU-7AohHIlajbg4eJRbLVMMDeyFpkYygUyvoDfFROKl-mQXCfT2K5gM-bfNRCO3EAlbN2JRzxE5NcnxA881KR4qEAPzX4SQ0GELnW1xKhZqA";
		String b = "ya29.GlteBxTEllniZO-MP0o-OxIqYPFclpVIZyK5lOcIdS7E50pYBA1dNujiBvmCKU8hHgZuN_9Zua8WcyhXwP9v5qGlJyu_FssfVhA1TsfkMOq4hDOmtHd02su4gewM";
		 GoogleCredential credential = new GoogleCredential().setAccessToken(b);
			
		 Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
	                .setApplicationName(APPLICATION_NAME)
	                .build();
		 
		
		
		 logger.info("upload");
		 File fileMetadata = new File();
		 fileMetadata.setName(newFile.getOriginalFilename());
		 System.out.print(newFile.getOriginalFilename());
		 String extension = FilenameUtils.getExtension(newFile.getOriginalFilename());
		 logger.info("mime");
		String mimeExtension = DriveHelperMimeType.getMime(extension);
		 fileMetadata.setMimeType(mimeExtension);
		 logger.info("mediacontent");
        
		 InputStream inputStream =  new BufferedInputStream(newFile.getInputStream());
		 
				 logger.info("service");
				 File file = service.files().create(fileMetadata,
                         new InputStreamContent(
                                 newFile.getContentType(),
                                 new ByteArrayInputStream(
                                IOUtils.toByteArray(inputStream))))
						   .setFields("id")
						    .execute();
				
				 //System.out.println("File ID: " + file.getId());
				 
				 return file.getId().length() > 0 ? file.getId() : null;
				 
		    	 
	}

}
