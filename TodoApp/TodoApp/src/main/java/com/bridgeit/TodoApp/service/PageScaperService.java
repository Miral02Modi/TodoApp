package com.bridgeit.TodoApp.service;

import java.util.List;

import com.bridgeit.TodoApp.model.PageScraper;

public interface PageScaperService {
	
	public PageScraper createScraper(PageScraper scraper) throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List  getScraperById(int id) throws Exception;
}
