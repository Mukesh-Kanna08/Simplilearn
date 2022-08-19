package com.simplilearn.models;

public class Student {
	
	private int id;
	private String fname;
	private String lname;
	private int age;
	private int student_class;
	
	
	
	
	public Student(int id, String fname, String lname, int age, int student_class) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.student_class = student_class;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAclass() {
		return student_class;
	}
	public void setAclass(int student_class) {
		this.student_class = student_class;
	}


	@Override
	public String toString() {
		return "Student [id=" + id + ", fname=" + fname + ", lname=" + lname + ", age=" + age + ", class=" + student_class
				+ "]";
	}
 
	

}
