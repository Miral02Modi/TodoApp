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
	public int userRegister(UserRegistration registration,String url) throws Exception {
		return dao.userRegister(registration,url);
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


	@Override
	@Transactional
	public UserRegistration checkUserAvailable(String email) throws Exception {
		return dao.checkUserAvailable(email);
	}


	@Override
	@Transactional
	public void verifyMail(int email) throws Exception {
		dao.verifyMail(email);
	}


	@Override
	@Transactional
	public int getUserId(String email) throws Exception {
		return dao.getUserId(email);
	}


	@Override
	@Transactional
	public void updatePassword(String password, int id) throws Exception {
		dao.updatePassword(password, id);
	}


	@Override
	@Transactional
	public void updateImage(UserRegistration user) throws Exception {
		dao.updateImage(user);
	}
	
}
