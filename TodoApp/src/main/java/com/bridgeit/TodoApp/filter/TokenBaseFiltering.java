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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bridgeit.TodoApp.Json.ErrorResponse;
import com.bridgeit.TodoApp.model.Token;
import com.bridgeit.TodoApp.service.TokenService;

public class TokenBaseFiltering implements Filter {
		
	/*@Autowired
	private TokenService tokenService;*/

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println(req.getServletContext());
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		System.out.println(applicationContext);
		TokenService tokenService = (TokenService) applicationContext.getBean("tokenService");
		
		/*WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(req.getServletContext());
		TokenService tokenService = applicationContext.getBean(TokenServiceImpl.class);*/
		System.out.println("token service :: "+tokenService);
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse responce = (HttpServletResponse) resp;
		
		System.out.println("filter token service :: "+tokenService);

		String accessToken = request.getHeader("accessToken");
		System.out.println("Header::" + accessToken);
		Cookie cookie[] = request.getCookies();
		Date currentDate = new Date();

		if (cookie != null) {
			System.out.println("indisde the cookie");
			for (Cookie cookie2 : cookie) 
			{
				if(cookie2.getName().equals("access_Token"))
				{
					accessToken = cookie2.getValue();
					System.out.println("access token get by Cookie :: "+accessToken);
				}
				System.out.println("Header::" + accessToken);
			}

		} else {

			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setStatus(-1);
			errorResponse.setMessage("User Not Found");
			String jsonResp = "{\"status\":\"-2\",\"errorMessage\":\"Invalid access token\"}";
			responce.setContentType("application/json");
			responce.getWriter().write(jsonResp);
			return;

		}

		//-------
		
		
		Token token = tokenService.getToken(accessToken);
		
		System.out.println("database token :: "+token);
		if (token == null) {

			responce.setContentType("application/json");
			String jsonResp = "{\"status\":\"-3\",\"errorMessage\":\"Invalid access token\"}";
			responce.getWriter().write(jsonResp);
			return;
		}

		Date oldDate = token.getCreateOn();
		long difference = currentDate.getTime() - oldDate.getTime();
		long differrenceInSecond = TimeUnit.MILLISECONDS.toSeconds(difference);

		if (differrenceInSecond > 60) {

			responce.setContentType("application/json");
			String jsonResp = "{\"status\":\"-4\",\"errorMessage\":\"Access token is expired. Generate new Access Tokens\"}";
			responce.getWriter().write(jsonResp);
			return;
		}
		chain.doFilter(request, responce);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	/*	
		ServletContext context = filterConfig.getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(context);
		AutowireCapableBeanFactory capableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
		capableBeanFactory.configureBean(this, "tokenService");*/
	}

}
