package net.javaguides.springboot.main.common.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import lombok.Synchronized;
import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.model.SheduledData;
import net.javaguides.springboot.main.common.serviceI.SheduledServiceI;
import net.javaguides.springboot.main.controller.ReturnsController;
import net.javaguides.springboot.main.utils.UtilsConstant;

@Service
public class SheduledService implements SheduledServiceI{

	@Autowired
	SheduledRepository sheduledRepo;
	
	@Autowired
	@Lazy
	SheduledTimeService sheduledTimeService;
	
	@Autowired
	@Lazy
	ReturnsController returnsMethod;


	public JSONObject saveUrlData(JSONObject json) throws JSONException {
		JSONObject status = new JSONObject(); 
		SheduledData sheduledData= new SheduledData();
		sheduledData.url= json.getString("url");
		sheduledData.nameOfUrl = json.getString("nameOfUrl");
		sheduledData.urlkeyName= json.getString("urlkeyName");
		sheduledData.updatedDate= sheduledTimeService.presentDateAndTime();

		//sheduledData.updatedDate=LocalDateTime.now();
		SheduledData sheduledDatasave = sheduledRepo.save(sheduledData);
		if(sheduledDatasave!=null) {
			status.put("status", "1005");
		}else {
			status.put("status", "1004");
		}
		return status;
	}

	public Integer checkUrlNameIsPresentOrNot(String urlName) {
		int findByUrlkeyName = sheduledRepo.countByUrlkeyName(urlName);
		return findByUrlkeyName;
	}

	public List<SheduledData> getListofUrlData() {
		List<SheduledData> sheduledData = sheduledRepo.findAll();
		return sheduledData;
	}

	public SheduledData updateSheduledData(JSONObject json) throws Exception {
		SheduledData sheduledData = sheduledRepo.findBysheduledId(json.getString("sheduledId"));
		sheduledData.sheduledId = json.getString("sheduledId");
		sheduledData.sheduledDate = json.getString("updateDate");
		SheduledData sheduledDataAfterSave = sheduledRepo.save(sheduledData);
		if(sheduledDataAfterSave != null) {
			String givenTimeIsGreaterOrEqual = sheduledTimeService.givenTimeIsGreaterOrEqual(sheduledDataAfterSave.sheduledDate);
			if(givenTimeIsGreaterOrEqual.equals("after")) {
				sheduledDataAfterSave.status="pending";
			}else if(givenTimeIsGreaterOrEqual.equals("equal")) {
				//String drc03DatagetDrc03List = returnsMethod.drc03DatagetDrc03List();
				Integer runMethodBasedOnMethodKeyName = runMethodBasedOnMethodKeyName(sheduledData.urlkeyName,sheduledData);
				if(runMethodBasedOnMethodKeyName != 0) {
					sheduledDataAfterSave.status="completed";
				}
			}else {
				
			}
			sheduledRepo.save(sheduledDataAfterSave);
		}

		return  sheduledRepo.findBysheduledId(json.getString("sheduledId"));
	}
	
	public SheduledData getDateByKeyName(String keyName) {
		SheduledData findByUrlkeyName = sheduledRepo.findByUrlkeyName(keyName);
		return findByUrlkeyName;
	}

	public void updateUrlUpdateDataByUrlKeyName(String urlKeyName) {
		int updateUrlDateByUrlKeyName = sheduledRepo.updateUrlDateByUrlKeyName(urlKeyName,sheduledTimeService.presentDateAndTime());
	}

	//Common 7297 Note: It well Run If sheduled Time is Equel 
	@Synchronized
	public Integer runMethodBasedOnMethodKeyName(String urlkeyName, SheduledData sheduledData) throws Exception {
		int updated = 0;
		switch (urlkeyName) {
			case "CRN_KEY":
				String drc03DatagetDrc03List = returnsMethod.drc03DatagetCrnList(sheduledData.sheduledDate);
				if(drc03DatagetDrc03List != null) {
					updated = 1;
				}
				break;
	        case "DRC03_KEY":
	        	String drc03DatagetCrnList = returnsMethod.drc03DatagetDrc03List(sheduledData.sheduledDate);
				if(drc03DatagetCrnList != null) {
					updated = 1;
				}
				break;
	        case "CRN_DRC03_KEY":
	        	String crenListandSaveDrc03Data = returnsMethod.generateCRNlistForDrc03SaveDrc03Data(sheduledData.sheduledDate,false);
				if(crenListandSaveDrc03Data != null) {
					updated = 1;
				}
				break;
	        case "CRN_DRC01_KEY":
	        	String crenListandSaveDrc01Data = returnsMethod.generateCRNlistForDrc01SaveDrc01Data(sheduledData.sheduledDate,false);
				if(crenListandSaveDrc01Data != null) {
					updated = 1;
				}
				break;
	        case "DRC07_LIST_KEY":
	        	String saveDrc07ListData = returnsMethod.generateDRC07ListForItemIDSaveDrc07DataByDemaindId(sheduledData.sheduledDate);
				if(saveDrc07ListData != null) {
					updated = 1;
				}
				break;
			default:
				break;
			}
		
		return updated;
	}
	
	


}
