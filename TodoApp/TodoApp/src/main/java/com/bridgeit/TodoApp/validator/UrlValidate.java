package com.bridgeit.TodoApp.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlValidate {
	
	public static final String URL_REGEX = "(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*"+
			"[-a-zA-Z0-9+&@#/%=~_|]";
	
	@SuppressWarnings("rawtypes")
	public static List isValidateUrl(String data) throws Exception{
		
		Pattern pattern = Pattern.compile(URL_REGEX);
		Matcher matcher = pattern.matcher(data);
		List<String> grpOfUrl = new ArrayList<String>();
		System.out.println("Groip of Url:::::::::"+grpOfUrl);
		while(matcher.find()){
			grpOfUrl.add(data.substring(matcher.start(),matcher.end()));
		} 
		return grpOfUrl;
		
	}
	
}
