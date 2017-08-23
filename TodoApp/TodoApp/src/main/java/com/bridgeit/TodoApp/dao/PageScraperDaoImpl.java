package com.bridgeit.TodoApp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgeit.TodoApp.model.PageScraper;

public class PageScraperDaoImpl implements PageScraperDao {
	
	@Autowired
	SessionFactory factory;
	
	@Override
	public PageScraper createScraper(PageScraper scraper) throws Exception {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(scraper);
		return scraper;
	}

	
	@Override
	@SuppressWarnings("rawtypes")
	public List getScraperById(int id) throws Exception {
		
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(PageScraper.class);
		Criterion criterion = Restrictions.eq("noteId", id);
		Conjunction conjunction = new Conjunction();
		conjunction.add(criterion);
		criteria.add(conjunction);

		return criteria.list();
	}

}
