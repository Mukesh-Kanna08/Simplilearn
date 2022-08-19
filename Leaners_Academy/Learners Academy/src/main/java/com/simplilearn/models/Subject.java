package com.simplilearn.models;

public class Subject {
	
	private int id;
	private String name;
	private String short_form;
	
	public Subject(int id, String name, String short_form ) {
		super();
		this.id = id;
		this.name = name;
		this.short_form = short_form;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getShortform() {
		return short_form;
	}

	public void setShortform(String short_form) {
		this.short_form = short_form;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
