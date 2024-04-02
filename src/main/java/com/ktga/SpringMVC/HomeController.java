package com.ktga.SpringMVC;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktga.SpringMVC.dao.studentDao;
import com.ktga.SpringMVC.model.student;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;


@Controller	
public class HomeController {
	
	@Autowired
	private studentDao sd;
	
	@ModelAttribute
	public void ObjectData(Model m) {
		m.addAttribute("name", "student");
	}
	
	@RequestMapping("/")
	public String home() {
		return "index";	
	}
	
	@RequestMapping("add")
	public String add(HttpServletRequest req, HttpServletResponse res) {
		
		int a = Integer.parseInt(req.getParameter("1"));
		int b = Integer.parseInt(req.getParameter("2"));
		int c = a+b;
		HttpSession session = req.getSession();
		session.setAttribute("c", c);
		return "result";	
	}
	
	@RequestMapping("add1")
	public String add1(@RequestParam("1") int a, @RequestParam("2") int b,HttpSession session) {
		
		int c = a+b;
		session.setAttribute("c", c);
		return "result";	
	}
	
	@RequestMapping("add2")
	public ModelAndView add2(@RequestParam("1") int a, @RequestParam("2") int b) {
		
		ModelAndView mv = new ModelAndView(); // or ModelAndView mv = new ModelAndView("result");  
		mv.setViewName("result");
		int c = a+b;
		mv.addObject("c", c);
		return mv ;	
	}
	
	@RequestMapping("add3")
	public String add3(@RequestParam("1") int a, @RequestParam("2") int b, Model m) { //ModelMap - work as same but give in map form
		  
		int c = a+b;
		m.addAttribute("c", c);
		return "result" ;	
	}
	
	@RequestMapping("addStudent")
	public String addStudent(@RequestParam("id") int a, @RequestParam("name") String b, Model m) { //ModelMap - work as same but give in map form
		  
		student st = new student();
		st.setId(a);
		st.setName(b);
		m.addAttribute("students",st);
		return "result" ;	
	}
	
	@RequestMapping("addStudent1")
	public String addStudent1(@ModelAttribute student st, Model m) {

		m.addAttribute("student",st);
		return "result" ;	
	}
	
	// if you use not use @modelattribute then JSTL must be class otherwise give @modelattribute
	
	@RequestMapping("addStudent2")
	public String addStudent2(@ModelAttribute("student") student s) {
		return "result";
	}
	
	@RequestMapping("addStudent3") // -- accepts both get and post
	public String addStudent3(student s) {
		return "result";
	}
	
	@RequestMapping(value="addStudent4", method=RequestMethod.GET) // or GetMapping()
	public String addStudent4(student s) {
		return "result";
	}
	
	@GetMapping("getStudentss")
	public String getStudent1(Model m) {
		List<student> li = Arrays.asList(new student(101, "Varun"), new student(102, "arun"));
		m.addAttribute("students", li);
		return "showStudents";
	}
	
	@GetMapping("getStudents")
	public String getStudent2(Model m) {
		m.addAttribute("students", sd.getStudents());
		return "showStudents";
	}
	
	@RequestMapping("addStudent5")
	public String addStudent5(@ModelAttribute("students") student s) {
		sd.addStudent(s);
		return "showStudents";
	}
	
	@GetMapping("getStudent")
	public String getStudent3(@RequestParam("id") int a,Model m) { // if int id means there is no need to specify at @requestParam
		m.addAttribute("students", sd.getStudent(a));
		return "showStudents";
	}
	
}
