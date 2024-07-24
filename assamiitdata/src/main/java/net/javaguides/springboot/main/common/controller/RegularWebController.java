package net.javaguides.springboot.main.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.model.SheduledData;
import net.javaguides.springboot.main.common.service.SheduledService;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class RegularWebController {
	
	@Autowired
	SheduledService  sheduledService;
	
    @GetMapping("/")
    public String redirectToJsp() {
        return "web_login";
    }

	@GetMapping("/loginDataUrl")
	public String loginData() {
		return "web_login";
	}
	
	@GetMapping("/dashbord")
	public String dashBord(Model model) throws JSONException {
		List<JSONObject> listJson= new LinkedList<JSONObject>();
		
		List<SheduledData> list= sheduledService.getListofUrlData();
		for(int i=0;i<list.size();i++) {
			JSONObject json= new JSONObject();
			json.put("sheduledId", list.get(i).sheduledId);
			json.put("sheduledDate", list.get(i).sheduledDate);
			json.put("nameOfUrl", list.get(i).nameOfUrl);
			json.put("urlkeyName", list.get(i).urlkeyName);
			json.put("updatedDate", list.get(i).updatedDate);
			json.put("status", list.get(i).status);
			json.put("url", list.get(i).url);
			listJson.add(json);	
		}
		model.addAttribute("listJson", listJson);
		return "web_dashbord";
	}
	
	@GetMapping("/getData")
	public String getData() {
		return "web_testing";
	}
}
