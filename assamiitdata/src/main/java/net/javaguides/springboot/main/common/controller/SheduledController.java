package net.javaguides.springboot.main.common.controller;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.model.SheduledData;
import net.javaguides.springboot.main.common.service.SheduledService;
import net.javaguides.springboot.main.common.service.SheduledTimeService;

@RestController
@RequestMapping("/sheduled")
public class SheduledController {

	@Autowired
    SheduledService sheduledService;
	
	@Autowired
	SheduledRepository sherepo;
	
	@Autowired
	SheduledTimeService sheduledTimeService;
	
	

//	@PostMapping(value = "/saveSheduledData", consumes = { "application/json", "application/x-www-form-urlencoded" }, produces = "application/json")
//	@ResponseBody
//	public String saveDate(@RequestBody MultiValueMap<String, String> formData, Model model) throws Exception {
//	    //Map<String, String> responseMap = new HashMap<>();
//		JSONObject jsons= new JSONObject();
//	    try {
//	        JSONObject json = new JSONObject(formData.toSingleValueMap().get("data"));
//	        sheduledService.updateSheduledData(json);
//	        jsons.put("status", "1005");
//	    } catch (Exception e) {
//	    	jsons.put("status", "error");
//	    	jsons.put("message", e.getMessage()); // Include an error message if needed
//	    }
//
//	    return jsons.toString();
//	}
	
	@PostMapping(value = "/saveSheduledData", consumes = { "application/json", "application/x-www-form-urlencoded" }, produces = "application/json")
	@ResponseBody
	public Map<String, String> saveDate(@RequestBody MultiValueMap<String, String> formData, Model model) throws Exception {
	    Map<String, String> responseMap = new HashMap<>();
	    JSONObject json = new JSONObject(formData.toSingleValueMap().get("data"));
	    try {
	        String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(json.getString("updateDate"));
	        if(givenTimeIsGreaterOrEqual.equals("after") || givenTimeIsGreaterOrEqual.equals("equal")) {	        	
	        	SheduledData updateSheduledData = sheduledService.updateSheduledData(json);
	        	responseMap.put("status", "1005");
	        	responseMap.put("statusValue", updateSheduledData.status);
	        	responseMap.put("sheduledDate", updateSheduledData.sheduledDate);
	        	responseMap.put("updatedDate", updateSheduledData.updatedDate);
	        }else {
	        	responseMap.put("status", "1004");
	        	responseMap.put("message","Date Should Be Greater Than current Date");
		        responseMap.put("sheduledId", json.getString("sheduledId"));
	        }

	    } catch (Exception e) {
	        responseMap.put("status", "error");
	        responseMap.put("message", e.getMessage()); 
	    }
	    return responseMap;
	}

//	@PostMapping(value = "/saveListSheduledData", consumes = { "application/json", "application/x-www-form-urlencoded" }, produces = "application/json")	
//	@ResponseBody
//	public Map<String, List<Map>> saveListDate(@RequestBody MultiValueMap<String, String> formData, Model model) throws Exception {
//		  Map<String, List<Map>> responseMap = new HashMap<>();
//		    try {
//		        JSONObject json = new JSONObject(formData.toSingleValueMap().get("data"));
//		        System.out.println(json+"--ss");
//                 JSONArray jsonArray = json.getJSONArray("listData");	
//                 List<Map> listMap= new ArrayList<Map>();
//                 for(int i=0;i<jsonArray.length();i++) {
//                	 Map<String,String> map= new HashMap<>();
//                	 try {
//                		 SheduledData updateSheduledData = sheduledService.updateSheduledData(jsonArray.getJSONObject(i));
//                		 map.put("status", "1005");
//                		 map.put("statusValue", updateSheduledData.status);
//                		 map.put("sheduledDate", updateSheduledData.sheduledDate);
//                		 map.put("updatedDate", updateSheduledData.updatedDate);
//                		 listMap.add(map);
//                	 }catch(Exception e) {
//                		 map.put("status", "error");
//                		 map.put("message", e.getMessage()); // Include an error message if needed
//                	 }
//                 }
//                 responseMap.put("obj", listMap);
//
//		    } catch (Exception e) {
//		    	
//		    }
//		  return responseMap;
//    }
	@PostMapping(value = "/saveListSheduledData", consumes = { "application/json", "application/x-www-form-urlencoded" }, produces = "application/json")
	@ResponseBody
	public Map<String, List<Map>> saveListDate(@RequestBody MultiValueMap<String, String> formData, Model model) throws Exception {
		  Map<String, List<Map>> responseMap = new HashMap<>();
          List<Map> listErrorMap= new ArrayList<Map>();
		    try {
		        JSONObject json = new JSONObject(formData.toSingleValueMap().get("data"));
		        String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(json.getString("updateDate"));
		        System.out.println(givenTimeIsGreaterOrEqual+"--givenTimeIsGreaterOrEqual");
		        if(givenTimeIsGreaterOrEqual.equals("after") || givenTimeIsGreaterOrEqual.equals("equal")) {
		            JSONArray jsonArray = json.getJSONArray("listData");	
	                 List<Map> listMap= new ArrayList<Map>();
	                 for(int i=0;i<jsonArray.length();i++) {
	                	 Map<String,String> map= new HashMap<>();
	                	 try {
	                		 SheduledData updateSheduledData = sheduledService.updateSheduledData(jsonArray.getJSONObject(i));
	                		 map.put("status", "1005");
	                		 map.put("statusValue", updateSheduledData.status);
	                		 map.put("sheduledDate", updateSheduledData.sheduledDate);
	                		 map.put("updatedDate", updateSheduledData.updatedDate);
	                		 listMap.add(map);
	                	 }catch(Exception e) {
	                		 map.put("status", "error");
	                		 map.put("message", e.getMessage()); // Include an error message if needed
	                	 }
	                 }
	                 responseMap.put("obj", listMap);   
		        }else {
                	 Map<String,String> map= new HashMap<>();
                	 map.put("status", "1004");
                	 map.put("message", "Date Should Be Greater Than current Date");
                	 listErrorMap.add(map);
                	 responseMap.put("obj", listErrorMap);
		        }
		    } catch (Exception e) {
		    	
		    }
		  return responseMap;
    }
	

}
