package com.bridgeit.TodoApp.dao;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.TodoApp.model.UserRegistration;
import com.bridgeit.TodoApp.validator.Password_Encrypt;;

/**
 * 
 * @author Miral
 *
 */

public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	SessionFactory factory;

	
	
	@Override
	public void userRegister(UserRegistration user) throws Exception {

		// -------Password Encryption
		Password_Encrypt encrypt = new Password_Encrypt();
		user.setPassword(encrypt.generateStorngPasswordHash(user.getPassword()));

		Session session = factory.getCurrentSession();
		session.saveOrUpdate(user);
	}

	
	@Override
	@Transactional
	public UserRegistration loginUser(String email, String password) throws Exception  {

		/*
		 * Session session = factory.getCurrentSession(); Criteria criteria =
		 * session.createCriteria(UserRegistration.class);
		 * 
		 * Criterion email = Restrictions.eq("email", registration.getEmail());
		 * Criterion password = Restrictions.eq("password",
		 * registration.getPassword());
		 * 
		 * LogicalExpression andExpression = Restrictions.and(email, password);
		 * criteria.add(andExpression); List<UserRegistration> list =
		 * criteria.list();
		 * 
		 * UserRegistration registration2 = list.get(0);
		 */

		// -------Using Conjunction
		/*
		 * Session session = factory.getCurrentSession(); Criteria criteria =
		 * session.createCriteria(UserRegistration.class);
		 * 
		 * Criterion criterion = Restrictions.eq("email", email); Criterion
		 * criterion2 = Restrictions.eq("password", password); Conjunction
		 * conjunction = new Conjunction();
		 * 
		 * conjunction.add(criterion); conjunction.add(criterion2);
		 * criteria.add(conjunction);
		 * 
		 * return (UserRegistration) criteria.uniqueResult();
		 */
		
		
		
		//-------Getting User-register object from database
		Password_Encrypt encrypt = new Password_Encrypt();
		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRegistration.class);

		Criterion criterion = Restrictions.eq("email", email);
		Conjunction conjunction = new Conjunction();

		conjunction.add(criterion);
		criteria.add(conjunction);
		UserRegistration user = (UserRegistration) criteria.uniqueResult();
		
		
		if(user == null)
			return null;
		
		//-------matching password
		System.out.println("validate password matching"+user.getPassword());
		boolean matched = false;
			matched = encrypt.validatePassword(password, user.getPassword());
		
		
		if (matched){
			return user;
		}	
		return null;
	}

	@Override
	public void updateProfile(UserRegistration user) throws Exception {
		
		
		Password_Encrypt encrypt = new Password_Encrypt();
		user.setPassword(encrypt.generateStorngPasswordHash(user.getPassword()));

		Session session = factory.getCurrentSession();
			
		Criteria criteria = session.createCriteria(UserRegistration.class);
		criteria.setProjection(Projections.property("id"));
		Criterion criterion = Restrictions.eq("email", user.getEmail());

		criteria.add(criterion);
		Integer id = (Integer) criteria.uniqueResult();
		System.out.println("Id" + id);
		user.setId(id);
		
		session.update(user);
	}

	@Override
	public UserRegistration getUserByID(String id) throws Exception {
		return null;
	}

}
