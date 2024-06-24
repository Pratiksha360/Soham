package com.taskmanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanagement.entity.Employee;
import com.taskmanagement.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	
	@Autowired(required=true)
	private EmployeeRepository repo;
	
	@GetMapping("/task")
	public String login(Model model) {
		Employee user=new Employee();
		model.addAttribute("user",user);
		return "loginform";
	}
	
	@PostMapping("/userLogin")
	public String loginUser(@ModelAttribute("user")Employee user) {
		String userId=user.getUserId();
		Optional<Employee> userdata=repo.findById(userId);
		if(user.getPassword().equals(userdata.get().getPassword())) {
			return "home";
		} else {
			return "error";
		}
	
	}

}
