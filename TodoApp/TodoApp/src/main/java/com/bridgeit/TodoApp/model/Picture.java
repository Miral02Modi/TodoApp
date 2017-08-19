package com.bridgeit.TodoApp.model;

public class Picture {
	
	private  FbPicURl data;

	public FbPicURl getData() {
		return data;
	}

	public void setData(FbPicURl data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Picture [data=" + data + "]";
	}
}
