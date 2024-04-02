package com.ktga.SpringMVC.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktga.SpringMVC.model.student;

import jakarta.transaction.Transactional;

@Component
public class studentDao {
	
	@Autowired
	private SessionFactory factory;
	
	@Transactional
	public List<student> getStudents(){
		Session session = factory.getCurrentSession();
		List<student> st = session.createQuery("from student", student.class).list();
		System.out.println(st);
		return st;
	}

	@Transactional
	public void addStudent(student s) {
		Session session = factory.getCurrentSession();
		session.save(s);
	}

	@Transactional
	public student getStudent(int a) {
		Session session = factory.getCurrentSession();
		return session.get(student.class, a);
	}
}
