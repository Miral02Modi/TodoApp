package com.bridgeit.TodoApp.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

	public int userRegister(UserRegistration user, String url) throws Exception {
		
		Session session = factory.getCurrentSession();
		
		if (url.equals("mannual")) {
			// -------Password Encryption
			Password_Encrypt encrypt = new Password_Encrypt();
			user.setPassword(encrypt.generateStorngPasswordHash(user.getPassword()));

			
			session.save( user );
			
			int id = user.getId();
			
			return id;
		}
		
		if( url.equals("facebook") ){
			session.saveOrUpdate(user);
		}
		return 0;
	}

	@Override
	@Transactional
	public UserRegistration loginUser(String email, String password) throws Exception {

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

		
		// -------Getting User-register object from database
		Password_Encrypt encrypt = new Password_Encrypt();
		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRegistration.class);

		Criterion criterion = Restrictions.eq("email", email);
		criteria.add(Restrictions.eq("varifyUser", "true"));
		
		Conjunction conjunction = new Conjunction();

		conjunction.add(criterion);
		criteria.add(conjunction);
		UserRegistration user = (UserRegistration) criteria.uniqueResult();

		if (user == null)
			return null;

		// -------matching password
		System.out.println("validate password matching" + user.getPassword());
		boolean matched = false;
		matched = encrypt.validatePassword(password, user.getPassword());

		if (matched) {
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

	@SuppressWarnings("rawtypes")
	public UserRegistration  checkUserAvailable(String email) throws Exception {
		
		Session session = factory.getCurrentSession(); 
		Query query = session.createQuery("from UserRegistration where email=:emailId");
		query.setParameter("emailId", email);
		List list = query.list();	
		return (UserRegistration) list.get(0);
	}

	@Override
	public UserRegistration getUserByID(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void verifyMail(int id) throws Exception {
		System.out.println("Id i::s:::"+id);
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("update UserRegistration set varifyUser=:varifyUser1 where id=:ID");
		query.setParameter("varifyUser1", "true");
		query.setParameter("ID", id);
		query.executeUpdate();
	}

	@Override
	public void updatePassword(String password,int id) throws Exception {
		System.out.println("id is::"+id);
		Password_Encrypt encrypt = new Password_Encrypt();
		password = encrypt.generateStorngPasswordHash(password);
		System.out.println("password is::::"+password);
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("update UserRegistration set password=:password1 where id=:ID");
		query.setParameter("password1", password);
		query.setParameter("ID", id);
		query.executeUpdate();
	}

	@Override
	public int getUserId(String email) throws Exception {
		
		Session  session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(UserRegistration.class);
		criteria.setProjection(Projections.property("id"));
		Criterion criterion = Restrictions.eq("email", email);
		criteria.add(criterion);
		int id = (Integer) criteria.uniqueResult();
		System.out.println("Id" + id);
		return id;
	}

	@Override
	public void updateImage(UserRegistration user) throws Exception {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("update UserRegistration set profilleImage=:profilleImage1 where id=:ID");
		query.setParameter("profilleImage1", user.getProfilleImage());
		query.setParameter("ID", user.getId());
		query.executeUpdate();
		System.out.println("Update successfully");
	}
}
