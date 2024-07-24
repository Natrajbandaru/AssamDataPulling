package net.javaguides.springboot.main.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.Repository.Drc03MainRepo;
import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.model.SheduledData;
import net.javaguides.springboot.main.common.service.SheduledService;
import net.javaguides.springboot.main.serviceI.ReturnsServiceI;

@Service
public class ReturnService  implements ReturnsServiceI{


	@Autowired
	Drc03MainRepo drco3MainRepo;
	
     @Autowired 
     SheduledService sheduledService;
     
     @Transactional
     public JSONObject saveServiceCrnList(JSONObject sheduledJson) throws JSONException {
 		JSONObject json = new JSONObject();
 		json.put("url", sheduledJson.getString("url"));
 		json.put("nameOfUrl", "CRN List");
 		json.put("urlkeyName", "CRN_KEY");
 		
 		Integer checkUrlNameIsPresentOrNot = sheduledService.checkUrlNameIsPresentOrNot("CRN_KEY");
		JSONObject saveUrlData = new JSONObject();
		if(checkUrlNameIsPresentOrNot != 0) {
			sheduledService.updateUrlUpdateDataByUrlKeyName("CRN_KEY");
		}
		if(checkUrlNameIsPresentOrNot==0) {		
			 saveUrlData = sheduledService.saveUrlData(json);
		} 		
 		return saveUrlData;
 	}
     
    @Transactional
	public JSONObject saveServiceDrc03(JSONObject sheduledJson) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("url", sheduledJson.getString("url"));
		json.put("nameOfUrl", "Drc03Data");   
		json.put("urlkeyName", "DRC03_KEY");

		
		Integer checkUrlNameIsPresentOrNot = sheduledService.checkUrlNameIsPresentOrNot("DRC03_KEY");
		JSONObject saveUrlData = new JSONObject();
		if(checkUrlNameIsPresentOrNot != 0) {
            //--UpdateDate 
			sheduledService.updateUrlUpdateDataByUrlKeyName("DRC03_KEY");
		}
		if(checkUrlNameIsPresentOrNot==0) {		
			 saveUrlData = sheduledService.saveUrlData(json);
		}
		return saveUrlData;
	}
    
    @Transactional
	public JSONObject saveSheduledServiceDrc03(JSONObject sheduledJson) {
		
		JSONObject json = new JSONObject();
		json.put("url", sheduledJson.getString("url"));
		json.put("nameOfUrl", "Drc03CrnData");
		json.put("urlkeyName", "CRN_DRC03_KEY");  //UrlKeyName

		
		Integer checkUrlNameIsPresentOrNot = sheduledService.checkUrlNameIsPresentOrNot("CRN_DRC03_KEY");
		JSONObject saveUrlData = new JSONObject();
		if(checkUrlNameIsPresentOrNot != 0) {
            //--UpdateDate 
			sheduledService.updateUrlUpdateDataByUrlKeyName("CRN_DRC03_KEY");
		}
		if(checkUrlNameIsPresentOrNot==0) {		
			 saveUrlData = sheduledService.saveUrlData(json);
		}
		return saveUrlData;
	}
    
   @Transactional
  public JSONObject saveSheduledServiceDrc01(JSONObject sheduledJson) {
		
		JSONObject json = new JSONObject();
		json.put("url", sheduledJson.getString("url"));
		json.put("nameOfUrl", "Drc01CrnData");
		json.put("urlkeyName", "CRN_DRC01_KEY");  //UrlKeyName

		
		Integer checkUrlNameIsPresentOrNot = sheduledService.checkUrlNameIsPresentOrNot("CRN_DRC01_KEY");
		JSONObject saveUrlData = new JSONObject();
		if(checkUrlNameIsPresentOrNot != 0) {
            //--UpdateDate 
			sheduledService.updateUrlUpdateDataByUrlKeyName("CRN_DRC01_KEY");
		}
		if(checkUrlNameIsPresentOrNot==0) {		
			 saveUrlData = sheduledService.saveUrlData(json);
		}
		return saveUrlData;
	}
   
   @Transactional
	public JSONObject saveSheduledServiceDrc07(JSONObject sheduledJson) {
		
		JSONObject json = new JSONObject();
		json.put("url", sheduledJson.getString("url"));
		json.put("nameOfUrl", "Drc07ListData");
		json.put("urlkeyName", "DRC07_LIST_KEY");  //UrlKeyName
	
		
		Integer checkUrlNameIsPresentOrNot = sheduledService.checkUrlNameIsPresentOrNot("DRC07_LIST_KEY");
		JSONObject saveUrlData = new JSONObject();
		if(checkUrlNameIsPresentOrNot != 0) {
	        //--UpdateDate 
			sheduledService.updateUrlUpdateDataByUrlKeyName("DRC07_LIST_KEY");
		}
		if(checkUrlNameIsPresentOrNot==0) {		
			 saveUrlData = sheduledService.saveUrlData(json);
		}
		return saveUrlData;
	}

	
     
}
