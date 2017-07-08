package com.bridgeit.TodoApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.service.RegistrationService;

/**
 * @author Miral
 *
 */
@Controller
public class RegisterController {

	@Autowired
	RegistrationService service;
	
	
	@RequestMapping(value = "/")
	public String landingPage(Model model) {
		model.addAttribute("register", new UserRegistration());
		return "index";
	}
	
	@RequestMapping(value = "/registerView", method = { RequestMethod.GET, RequestMethod.POST })
	public String registerView(Model model) {
		model.addAttribute("register", new UserRegistration());
		return "index";
	}
	
	@RequestMapping(value = "/loginView", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginView(Model model) {
		model.addAttribute("login", new UserRegistration());
		return "Login";
	}
}
