package com.bridgeit.TodoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.TodoApp.dao.PageScraperDao;
import com.bridgeit.TodoApp.model.PageScraper;

public class PageScraperImpl implements PageScaperService {
	
	@Autowired
	PageScraperDao scraperDao;
	
	@Override
	@Transactional
	public PageScraper createScraper(PageScraper scraper) throws Exception {
		return scraperDao.createScraper(scraper);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List getScraperById(int id) throws Exception {
		return scraperDao. getScraperById(id);
	}
}
