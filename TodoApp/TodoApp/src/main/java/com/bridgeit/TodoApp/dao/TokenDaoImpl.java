package com.bridgeit.TodoApp.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.TodoApp.model.Token;

/**
 * @author Miral
 *
 */
public class TokenDaoImpl implements TokenDao {

	@Autowired
	SessionFactory factory;

	@Override
	@Transactional
	public Token addToken(Token token) throws Exception {

		System.out.println("inside the token dao" + factory);
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(token);
		return token;
	}

	@Override
	public Token getToken(String accesToken) throws Exception {
		
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Token.class);
		Criterion criterion = Restrictions.eq("accessToken", accesToken);

		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);
		criteria.add(conjunction);
		Token token = (Token) criteria.uniqueResult();
		System.out.println("Access token:::"+token);
		return token;
	}
	
	
	@Override
	public Token getTokenByRefreshToken(String refreToken) throws Exception {
		
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Token.class);
		Criterion criterion = Restrictions.eq("refreshToken", refreToken);

		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);
		criteria.add(conjunction);
	
		return (Token) criteria.uniqueResult();
	}
	
	
	@Transactional
	public Token deleteToken(Token token) throws Exception{
		System.out.println("Token id is::"+token.getUserId());
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("delete from Token where userId =:userID");
		query.setParameter("userID", token.getUserId());
		return token;
	}
	
	
	
	/*@Override
	public Token getToken1(String accesToken) {
		
		SessionFactory factory = (SessionFactory) new LocalSessionFactoryBean();
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Token.class);
		Criterion criterion = Restrictions.eq("accessToken", accesToken);

		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);
		criteria.add(conjunction);
		Token token =(Token) criteria.uniqueResult();
		System.out.println("Token dao"+token);
		return token;

	}*/


}
