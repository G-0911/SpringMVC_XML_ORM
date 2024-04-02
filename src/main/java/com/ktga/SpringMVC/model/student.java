package com.ktga.SpringMVC.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class student {
	
	@Id
	int id;
	String name;
	public student() {
		
	}
	public student(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "student [id=" + id + ", name=" + name + "]";
	}
}
