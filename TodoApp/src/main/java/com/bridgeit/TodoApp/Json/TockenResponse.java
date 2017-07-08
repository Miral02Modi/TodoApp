package com.bridgeit.TodoApp.Json;

/**
 * @author Miral
 *
 */
public class TockenResponse extends Response {
	
	String accessTocken;
	String refreshTocken;
	
	
	public String getAccessTocken() {
		return accessTocken;
	}
	public void setAccessTocken(String accessTocken) {
		this.accessTocken = accessTocken;
	}
	
	
	public String getRefreshTocken() {
		return refreshTocken;
	}
	public void setRefreshTocken(String refreshTocken) {
		this.refreshTocken = refreshTocken;
	}
	
	
	@Override
	public String toString() {
		return "TockenResponse [accessTocken=" + accessTocken + ", refreshTocken=" + refreshTocken + "]";
	}
	
}
