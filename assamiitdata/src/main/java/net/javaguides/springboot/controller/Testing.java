package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.main.common.LoginRepository.SignUpRepository;
import net.javaguides.springboot.main.common.model.SignUpData;

@RestController
@RequestMapping("/test")
public class Testing {
	
	@Autowired
	 SignUpRepository signUpRepository;
	
	@GetMapping("/save")
	public String sendData() {
		for(int i=0;i<100000;i++) {
			SignUpData s= new SignUpData();
			s.name="aa"+i;
			signUpRepository.save(s);
		}
		return "Hello";
	}

}
