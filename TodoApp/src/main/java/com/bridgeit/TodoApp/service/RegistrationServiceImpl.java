package com.bridgeit.TodoApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.TodoApp.dao.RegistrationDao;
import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public class RegistrationServiceImpl implements RegistrationService {	
	
	@Autowired
	RegistrationDao dao;	
	
	@Override
	@Transactional
	public void userRegister(UserRegistration registration) throws Exception {
		dao.userRegister(registration);
	}

	
	@Override
	@Transactional
	public UserRegistration loginUser(String email,String password) throws Exception {
		return dao.loginUser(email,password);
	}
	
	
	@Override
	@Transactional
	public void updateProfile(UserRegistration registration) throws Exception {
		 dao.updateProfile(registration);
	}




	
	@Override
	public UserRegistration getUserbyId(String id) throws Exception {
		return	dao.getUserByID(id);
	}
	
}
