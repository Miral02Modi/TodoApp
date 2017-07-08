package com.bridgeit.TodoApp.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.TodoApp.model.UserRegistration;

/**
 * @author Miral
 *
 */
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	SessionFactory factory;

	@Override
	public void userRegister(UserRegistration registration) throws Exception {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(registration);
	}

	@Override
	@Transactional
	public UserRegistration loginUser(String email,String password) {

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

		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRegistration.class);

		Criterion criterion = Restrictions.eq("email", email);
		Criterion criterion2 = Restrictions.eq("password", password);
		Conjunction conjunction = new Conjunction();

		conjunction.add(criterion);
		conjunction.add(criterion2);
		criteria.add(conjunction);
	
		return (UserRegistration) criteria.uniqueResult();
	}

}
