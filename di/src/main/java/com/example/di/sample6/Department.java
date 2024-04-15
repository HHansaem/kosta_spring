package com.example.di.sample6;

public class Department {
	private Integer id;
	private String name;
	private String location;
	
	public Department(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	
	
	
}
