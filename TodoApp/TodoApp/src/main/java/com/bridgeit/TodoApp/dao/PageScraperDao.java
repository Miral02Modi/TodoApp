package com.bridgeit.TodoApp.dao;

import java.util.List;

import com.bridgeit.TodoApp.model.PageScraper;

public interface PageScraperDao {
	
	public PageScraper createScraper(PageScraper scraper) throws Exception;
	
	public List  getScraperById(int id) throws Exception;
}
