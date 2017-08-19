package com.bridgeit.TodoApp.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.springframework.http.MediaType;

import com.bridgeit.TodoApp.model.FBToken;
import com.bridgeit.TodoApp.model.FaceBookProfile;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FacebookUtil {

	public static final String App_Id = "462155094166865";
	public static final String Secret_Id = "96dd44e26cd717aaa04bcdeedd64cb7c";
	public static final String Redirect_URI = "http://localhost:8080/TodoApp/redirectFBURL";
	public static String FB_GET_USER_URL = "https://graph.facebook.com/v2.9/me?access_token=";
	public static String  binding = "&fields=id,name,email,first_name,last_name,picture";

	public String getFacebookURl() {

		String FBUrl = "";

		try {
			FBUrl = "https://www.facebook.com/v2.10/dialog/oauth?client_id=" + App_Id + "&redirect_uri="
					+ URLEncoder.encode(Redirect_URI, "UTF-8")+"&state="+123456789+"&response_type=code"+"&scope=public_profile,email";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return FBUrl;
	}

	public String getFBAccessToken(String code) {

		String FBurlForAccessToken = "";
		try {
			FBurlForAccessToken = "https://graph.facebook.com/v2.10/oauth/access_token?" + "client_id=" + App_Id
					+ "&redirect_uri=" + URLEncoder.encode(Redirect_URI, "UTF-8") + "&client_secret=" + Secret_Id
					+ "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		ResteasyClient client = new ResteasyClientBuilder().build();
		ResteasyWebTarget target = client.target(FBurlForAccessToken);
		Response response = target.request().accept(MediaType.APPLICATION_JSON_VALUE).get();
		FBToken fbToken = response.readEntity(FBToken.class);
		System.out.println("faceBook Token Is:::" + fbToken);
		
		client.close();
		return fbToken.getAccess_token();
	}	
	
	
	public static FaceBookProfile getFBProfile(String accessToken){
			
		ResteasyClient client1 = new ResteasyClientBuilder().build();
		ResteasyWebTarget target1 = client1.target(FB_GET_USER_URL+accessToken+binding);
		Response response1 = target1.request().accept(MediaType.APPLICATION_JSON_VALUE).get();
		
		String string = response1.readEntity(String.class);
		ObjectMapper mapper = new ObjectMapper();
		FaceBookProfile profile=null;
		
		try {
			profile = mapper.readValue(string, FaceBookProfile.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		client1.close();
		System.out.println("Facebook Profile Is::"+profile);

		return profile;
	}
}
