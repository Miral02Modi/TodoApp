package com.bridgeit.TodoApp.filter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bridgeit.TodoApp.Json.ErrorResponse;
import com.bridgeit.TodoApp.model.Token;
import com.bridgeit.TodoApp.service.TokenService;


/**
 * @author Miral
 *
 */
public class TokenBaseFiltering implements Filter {

	
	

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
			
		System.out.println("Inside the Filter");
		
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		/*Jedis jedis = new Jedis("localhost");*/
		/*System.out.println("jedis is::"+jedis.get("tokenAccess"));*/

		TokenService tokenService = (TokenService) applicationContext.getBean("tokenService");

		System.out.println("Inside the filter::::");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse responce = (HttpServletResponse) resp;
		Date currentDate = new Date();
		

		

		String accessToken =request.getHeader("accToken");
		System.out.println("accessToken using set Header::::" + accessToken);
	    
		

/*		Cookie cookie[] = request.getCookies();*/
		

		/*if (accessToken != null || cookie != null) {
			for (Cookie cookie2 : cookie) {
				if (cookie2.getName().equals("access_Token")) {
					accessToken = cookie2.getValue();

				}

			}

		}*/ 
		
		
		//------validate the accessToken is available or not
		if(accessToken == null || accessToken.trim().isEmpty()) {

			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("User Not Found");
			String jsonResp = "{\"status\":\"-2\",\"errorMessage\":\"Invalid access token\"}";
			responce.setContentType("application/json");
			responce.getWriter().write(jsonResp);
			return;

		}

		// -------
		Token token = tokenService.getToken(accessToken);
		
		
		
		//-----Checking the 
		if (token == null) {

			/*try {
				tokenService.deleteToken(token);
			} catch (Exception e) {
				e.printStackTrace();
			}*/

			responce.setContentType("application/json");
			String jsonResp = "{\"status\":\"-3\",\"errorMessage\":\"Invalid access token\"}";
			responce.getWriter().write(jsonResp);
			return;
		}

		Date oldDate = token.getCreateOn();
		long difference = currentDate.getTime() - oldDate.getTime();
		long differrenceInSecond = TimeUnit.MILLISECONDS.toSeconds(difference);
	
		System.out.println(differrenceInSecond);
		
		if (differrenceInSecond > 60*60*24) {
			System.out.println("session has been expired");
			// System.out.println("Filter"+differrenceInSecond);
			/*if (differrenceInSecond < 2 * 60 && !token.getRefreshToken().equals(token.getAccessToken())) {
				System.out.println("inside the refresh Token");
				// accessToken = token.getRefreshToken();
				token.setAccessToken(token.getRefreshToken());
				token.setCreateOn(new Date());
				
				Cookie cookie1 = new Cookie("access_Token", token.getAccessToken());
				responce.addCookie(cookie1);
				
				
				
				return;
			} else {*/
				
				responce.setContentType("application/json");
				String jsonResp = "{\"status\":\"-4\",\"errorMessage\":\"Access token is expired. Generate new Access Tokens\"}";
				responce.getWriter().write(jsonResp);
				return;
			

		}
	
		 /*token.setCreateOn(new Date()); try { tokenService.addToken(token); }
		 catch (Exception e) { e.printStackTrace(); }*/
		System.out.println("do chain");
		chain.doFilter(request, responce);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
