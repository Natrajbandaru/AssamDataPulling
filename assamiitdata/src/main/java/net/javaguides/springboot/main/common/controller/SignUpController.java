package net.javaguides.springboot.main.common.controller;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import net.javaguides.springboot.main.common.serviceI.SignUpServiceI;

@RestController
@RequestMapping("/mainLogin")
public class SignUpController {
 
	@Autowired
	SignUpServiceI signUpService;
	
	@Autowired
	CommonMethodes methods;
	
//	 @Value("${url}")
//	 private String url;
	 


	@PostMapping("/login") 
    @ResponseBody
	public String loginData(HttpServletRequest request, Model model) throws JSONException {
	     JSONObject json=new JSONObject(request.getParameter("data"));
	     return signUpService.LoginService(json);
	}
	
	@PostMapping("/signUp") 
    @ResponseBody
	public String signUpData(HttpServletRequest request, Model model) throws JSONException {
	     JSONObject json=new JSONObject(request.getParameter("data"));
	     return signUpService.SignUpService(json);
	}
	

}
