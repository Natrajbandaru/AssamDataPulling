package net.javaguides.springboot.main.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

import lombok.Synchronized;
import net.javaguides.springboot.Drc01Model.Drc01Main;
import net.javaguides.springboot.Drc03Model.Drc03Main;
import net.javaguides.springboot.Drc07Model.Drc07Main;
import net.javaguides.springboot.Repository.Drc01MainRepo;
import net.javaguides.springboot.Repository.Drc03MainRepo;
import net.javaguides.springboot.Repository.Drc07MainRepo;
import net.javaguides.springboot.main.common.LoginRepository.DailyReportDataRepo;
import net.javaguides.springboot.main.common.controller.CommonMethodes;
import net.javaguides.springboot.main.common.controller.DateListCommonMethods;
import net.javaguides.springboot.main.common.model.DailyReportDataModel;
import net.javaguides.springboot.main.common.service.DailyReportService;
import net.javaguides.springboot.main.common.service.SheduledService;
import net.javaguides.springboot.main.service.ReturnService;
import net.javaguides.springboot.main.utils.UtilsConstant;

@RestController
@RequestMapping("/returns")
public class ReturnsController {
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	Drc03MainRepo drco3MainRepo;
	
	@Autowired
	Drc01MainRepo drc01MainRepo;
	
	@Autowired
	Drc07MainRepo drc07MainRepo;
	
	@Autowired
	CommonMethodes commonMethodes;
	
	@Autowired
	ReturnService returnService;
	
	@Autowired
	DateListCommonMethods dateListCommonMethods;
	
	@Autowired
	DailyReportDataRepo  dailyDataRepo;
	
	@Autowired
	DailyReportService dailyReportService;
	
//	@Scheduled(fixedRate = (1000)*60) // Runs every 6 seconds
//    public void myScheduledMethod() throws Exception {
//        // Your scheduled task code here
//        System.out.println("Task executed every 6 seconds");
////        generateCRNlistForDrc03SaveDrc03Data("");
////        generateCRNlistForDrc01SaveDrc01Data("", null);
////        generateDRC07ListForItemIDSaveDrc07DataByDemaindId("");
//        myScheduledMethod1();
//        myScheduledMethod2();
//        myScheduledMethod3();
//    }
//	int a=10;
	 @Scheduled(cron = "0 49 18 * * ?") // Runs every 6 seconds
	public void myScheduledMethod1() {
		System.out.println("METHOD1");
	}
//	
//	@Scheduled(fixedRate = (1000)*60) // Runs every 6 seconds
//    public void myScheduledMethod3(String b) {
//		System.out.println("METHOD3");
//   	}
	
//	@Scheduled(cron = "21 17 * * MON-SUN") // Runs every 6 seconds
//    public void myScheduledMethod2() {
//		System.out.println("METHOD2");
//		int a = 10;
//		int b = 0;
//		int result = a / b;
//		System.out.println(result);
//	}
//	

	
//	public String generateCRNlist(String preveousDayofGivenDate) throws Exception {
//		String generateAuthToken = commonMethodes.generateAuthToken();
//		JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);
//
//		final String response = commonMethodes.getClient().resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v1.0/caselst?state_cd=18&start_tm=2023-10-11:11:16&end_tm=2023-10-11:12:16&action=CRNLST&casetyp=ADJVP&iseod=N").toString())
//				.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
//				.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
//				
//				.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
//				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
//		
//		String decodeBase64StringToString = commonMethodes.decodeBase64StringToString(new JSONObject(response).getString("data"));
//		System.out.println("Response Data CRNLST:: " + decodeBase64StringToString);
//		commonMethodes.getClient().destroy();
//		return decodeBase64StringToString;
//	}
	
	@GetMapping(value = "/getDrc03")
	public String drc03DatagetDrc03List(String Date) throws Exception {
		//AD1810230079390
		StringBuffer urlString= new StringBuffer();
		urlString.append(UtilsConstant.commonUrl);
		urlString.append("v1.0/adj/m2?");
		urlString.append("state_cd=18&crn=AD181220001833M&action=GETCASEDATAASSMT");
       
		//After Getting Response In StringOfJSON It well convert into Model 
		String drc03Itemsvpappdata = commonMethodes.getDecreptedUrlDataByUrl(urlString);
		System.out.println("11Drc03Data --"+drc03Itemsvpappdata);
        Drc03Main drc03Itemsvpappdatas = objectMapper.readValue(drc03Itemsvpappdata, Drc03Main.class);
        
        
        //Saveing TO Sheduling 
        JSONObject sheduledJson= new JSONObject();
		sheduledJson.put("url", "/returns/getDrc03");
		JSONObject saveUrlData = returnService.saveServiceDrc03(sheduledJson);
        
        //Saving Drc03
		Drc03Main save = drco3MainRepo.save(drc03Itemsvpappdatas);
		return drc03Itemsvpappdata;
	}
	
	@GetMapping(value = "/getCrn")
	public String drc03DatagetCrnList(String sheduledDate) throws Exception {
		String preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDate(sheduledDate);
		String generateCRNlist = generateCRNlistForDrc03SaveDrc03Data(preveousDayofGivenDate,false);
        //Saveing TO Sheduling 
		JSONObject sheduledJson= new JSONObject();
		sheduledJson.put("url", "/returns/getCrn");
		//sheduledJson.put("methodName", "returnsController.drc03DatagetCrnList()");
		JSONObject saveUrlData = returnService.saveServiceCrnList(sheduledJson);
		return generateCRNlist;
	}
	
	@Synchronized
	@GetMapping(value = "/saveDrc03DateWisedata")
	public void DateListDrc03() throws Exception {
//		List<String> dateRange = dateListCommonMethods.getDateRangeInReverseOutput("2023-12-02", "2024-01-01");
		List<String> dateRange = dateListCommonMethods.getDateRangeInReverseOutput("2024-03-02", "2024-04-17");
		System.out.println(dateRange+"--dateRange");
		JSONArray arr= new JSONArray();
		System.out.println(dateRange);
		for(int i=0;i<dateRange.size();i++) {
			generateCRNlistForDrc03SaveDrc03Data(dateRange.get(i),true);
//			JSONArray generateCRNlistForDrc01SaveDrc01DataFor = generateCRNlistForDrc01SaveDrc01DataFor(dateRange.get(i),true);
//			arr.put(generateCRNlistForDrc01SaveDrc01DataFor);
			//man();
//			   String preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateWithoutSec(dateRange.get(i));		
//			   System.out.println(preveousDayofGivenDate+"--preveousDayofGivenDate");
//				List<String> twentyFourHrsLoopForPericularDate = commonMethodes.twentyFourHrsLoopForPericularDate(preveousDayofGivenDate);
//				System.out.println(twentyFourHrsLoopForPericularDate+"--twentyFourHrsLoopForPericularDate");

		}	
		System.out.println("arr--"+arr);
	}
	
	//From Heare 
	@Synchronized
	@GetMapping(value = "/getCrnListforDrc03SaveDrc03")
	public String generateCRNlistForDrc03SaveDrc03Data(String sheduledDate, Boolean loopDate) throws Exception {
	
        try {        	
        	String preveousDayofGivenDate = "";
	        JSONArray jsonArr=new JSONArray();
			if(loopDate) {
				 preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateWithoutSec(sheduledDate);			
			}else {
				 preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDate(sheduledDate);			
			}
			
        	
        	List<String> twentyFourHrsLoopForPericularDate = commonMethodes.twentyFourHrsLoopForPericularDate(preveousDayofGivenDate);
        	int i=0;
        	while(i<twentyFourHrsLoopForPericularDate.size()) {
        		System.out.println(twentyFourHrsLoopForPericularDate.get(i)+"---"+twentyFourHrsLoopForPericularDate.get(i+1));
        		
        		String generateAuthToken = commonMethodes.generateAuthToken();
        		System.out.println(generateAuthToken+"---generateAuthToken");
        		JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);
        		final String response = commonMethodes.getClient().resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v1.0/caselst?state_cd=18&start_tm="+twentyFourHrsLoopForPericularDate.get(i)+"&end_tm="+twentyFourHrsLoopForPericularDate.get(i+1)+"&action=CRNLST&casetyp=ADJVP&iseod=N").toString())
        				.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
        				.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
        				.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
        				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
        		
        		JSONObject json=new JSONObject(response);
        		
        		JSONObject jsonPath=new JSONObject();
				jsonPath.put("crnFromDate", twentyFourHrsLoopForPericularDate.get(i));
				jsonPath.put("crnToDate", twentyFourHrsLoopForPericularDate.get(i+1));
				
        		if(json.getString("status_cd").equals("1")) {
        			String decodeBase64StringToString = commonMethodes.decodeBase64StringToString(new JSONObject(response).optString("data"));
        			Drc03DataFunction(decodeBase64StringToString,jsonPath.toString());
        		}
			    DailyReportDataModel dailyData=new DailyReportDataModel();
			    dailyReportService.dailyreport("Drc03", jsonPath.toString(),preveousDayofGivenDate);
			    commonMethodes.getClient().destroy();
			    
        		i=i+2;
        	}
        }catch(Exception e) {
        	
        }
		//Update  sheduled Date and Url Name Table Name :common_sheduled_data
		JSONObject sheduledJson= new JSONObject();
		sheduledJson.put("url", "/returns/getCrnListforDrc03SaveDrc03");
		JSONObject saveUrlData = returnService.saveSheduledServiceDrc03(sheduledJson);
		return "";
	}
	
	@Synchronized
	public void Drc03DataFunction(String decodeBase64StringToString,String pathParam) throws Exception {
		try {			
			if(decodeBase64StringToString != null) {
				
				JSONObject jsondata = new JSONObject(decodeBase64StringToString);
				JSONArray jsonArray = jsondata.getJSONArray("crnlist");
				for(int j=0;j<jsonArray.length();j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					
					String crnNo = jsonObject.getString("crn");
					System.out.println("Crns--"+crnNo);
					
					
					//AD1810230079390
					StringBuffer urlString= new StringBuffer();
					urlString.append(UtilsConstant.commonUrl);
					urlString.append("v1.0/adj/m2?");
					urlString.append("state_cd=18&crn="+crnNo+"&action=GETCASEDATAASSMT");
					//After Getting Response In StringOfJSON It well convert into Model 
					String drc03Itemsvpappdata = commonMethodes.getDecreptedUrlDataByUrl(urlString);
					if(drc03Itemsvpappdata!="0") {
						Drc03Main drc03Itemsvpappdatas = objectMapper.readValue(drc03Itemsvpappdata, Drc03Main.class);
						drc03Itemsvpappdatas.path_params = pathParam;
						//Saving Drc03
						Drc03Main save = drco3MainRepo.save(drc03Itemsvpappdatas);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Synchronized
	@GetMapping(value = "/saveDrc01DateWisedata")
	public void DateListDrc01() throws Exception {
//		List<String> dateRange = dateListCommonMethods.getDateRangeInReverseOutput("2023-12-02", "2024-01-01");
		List<String> dateRange = dateListCommonMethods.getDateRangeInReverseOutput("2024-03-02", "2024-04-17");
		System.out.println(dateRange+"--dateRange");
		JSONArray arr= new JSONArray();
		System.out.println(dateRange);
		for(int i=0;i<dateRange.size();i++) {
			generateCRNlistForDrc01SaveDrc01Data(dateRange.get(i),true);
//			JSONArray generateCRNlistForDrc01SaveDrc01DataFor = generateCRNlistForDrc01SaveDrc01DataFor(dateRange.get(i),true);
//			arr.put(generateCRNlistForDrc01SaveDrc01DataFor);
			//man();
//			   String preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateWithoutSec(dateRange.get(i));		
//			   System.out.println(preveousDayofGivenDate+"--preveousDayofGivenDate");
//				List<String> twentyFourHrsLoopForPericularDate = commonMethodes.twentyFourHrsLoopForPericularDate(preveousDayofGivenDate);
//				System.out.println(twentyFourHrsLoopForPericularDate+"--twentyFourHrsLoopForPericularDate");

		}	
		System.out.println("arr--"+arr);
	}
	
	
	//For Drc01
		@Synchronized
	//	@GetMapping(value = "/getCrnListforDrc01SaveDrc01DateWise")
		@Transactional
		public String generateCRNlistForDrc01SaveDrc01DataDateWise(String sheduledDate,Boolean loopDate) throws Exception {
			try {
//					String preveousDayofGivenDate = "";
//			        JSONArray jsonArr=new JSONArray();
//					if(loopDate) {
//						 preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateWithoutSec(sheduledDate);			
//					}else {
//						 preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDate(sheduledDate);			
//					}
////					
//					List<String> twentyFourHrsLoopForPericularDate = commonMethodes.twentyFourHrsLoopForPericularDate(preveousDayofGivenDate);
//					int i=0;
//					while(i<twentyFourHrsLoopForPericularDate.size()) {
					//	System.out.println(twentyFourHrsLoopForPericularDate.get(i)+"---"+twentyFourHrsLoopForPericularDate.get(i+1));
				String fromDt="2023-12-31:22:00";
				String toDate="2023-12-31:23:00";
			
						String generateAuthToken = commonMethodes.generateAuthToken();
						JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);
						final String response = commonMethodes.getClient().resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v1.0/caselst?state_cd=18&start_tm="+fromDt+"&end_tm="+toDate+"&action=CRNLST&casetyp=ADJDT&iseod=N").toString())
								.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
								.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
								.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
								.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
						
						JSONObject json=new JSONObject(response);
						JSONObject jsonPath=new JSONObject();
						jsonPath.put("crnFromDate", fromDt);
						jsonPath.put("crnToDate", toDate);
						if(json.getString("status_cd").equals("1")) {
							String decodeBase64StringToString = commonMethodes.decodeBase64StringToString(new JSONObject(response).optString("data"));
//							JSONObject jsonObject=new JSONObject();
//							jsonObject.put("DateOfParams", ""+twentyFourHrsLoopForPericularDate.get(i)+"+ , +"+twentyFourHrsLoopForPericularDate.get(i+1)+"");
//							jsonObject.put("decodeBase64StringToString", decodeBase64StringToString);
//							jsonArr.put(jsonObject);
							Drc01DataFunction(decodeBase64StringToString,jsonPath.toString());
							commonMethodes.getClient().destroy();
						}
						DailyReportDataModel dailyData=new DailyReportDataModel();
					    dailyReportService.dailyreport("Drc01", jsonPath.toString(),"2023-12-31");
						//i=i+2;
						
			 // }
			}
			 catch(Exception e) {
				e.printStackTrace();
			}
			//Update  sheduled Date and Url Name Table Name :common_sheduled_data
			JSONObject sheduledJson= new JSONObject();
			sheduledJson.put("url", "/returns/getCrnListforDrc01SaveDrc01");
			JSONObject saveUrlData = returnService.saveSheduledServiceDrc01(sheduledJson);
			return "";
		}
		

	

	
		
	
	//For Drc01
	@Synchronized
	@GetMapping(value = "/getCrnListforDrc01SaveDrc01")
	@Transactional
	public String generateCRNlistForDrc01SaveDrc01Data(String sheduledDate,Boolean loopDate) throws Exception {
		try {
				String preveousDayofGivenDate = "";
		        JSONArray jsonArr=new JSONArray();
				if(loopDate) {
					 preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateWithoutSec(sheduledDate);			
				}else {
					 preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDate(sheduledDate);			
				}
				
				List<String> twentyFourHrsLoopForPericularDate = commonMethodes.twentyFourHrsLoopForPericularDate(preveousDayofGivenDate);
				int i=0;
				while(i<twentyFourHrsLoopForPericularDate.size()) {
					System.out.println(twentyFourHrsLoopForPericularDate.get(i)+"---"+twentyFourHrsLoopForPericularDate.get(i+1));
		
					String generateAuthToken = commonMethodes.generateAuthToken();
					JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);
					final String response = commonMethodes.getClient().resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v1.0/caselst?state_cd=18&start_tm="+twentyFourHrsLoopForPericularDate.get(i)+"&end_tm="+twentyFourHrsLoopForPericularDate.get(i+1)+"&action=CRNLST&casetyp=ADJDT&iseod=N").toString())
							.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
							.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
							.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
							.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
					
					JSONObject json=new JSONObject(response);
					JSONObject jsonPath=new JSONObject();
					jsonPath.put("crnFromDate", twentyFourHrsLoopForPericularDate.get(i));
					jsonPath.put("crnToDate", twentyFourHrsLoopForPericularDate.get(i+1));
					if(json.getString("status_cd").equals("1")) {
						String decodeBase64StringToString = commonMethodes.decodeBase64StringToString(new JSONObject(response).optString("data"));
//						JSONObject jsonObject=new JSONObject();
//						jsonObject.put("DateOfParams", ""+twentyFourHrsLoopForPericularDate.get(i)+"+ , +"+twentyFourHrsLoopForPericularDate.get(i+1)+"");
//						jsonObject.put("decodeBase64StringToString", decodeBase64StringToString);
//						jsonArr.put(jsonObject);
						Drc01DataFunction(decodeBase64StringToString,jsonPath.toString());
						commonMethodes.getClient().destroy();
					}
					DailyReportDataModel dailyData=new DailyReportDataModel();
				    dailyReportService.dailyreport("Drc01", jsonPath.toString(),preveousDayofGivenDate);
					i=i+2;
					
		  }
		}
		 catch(Exception e) {
			e.printStackTrace();
		}
		//Update  sheduled Date and Url Name Table Name :common_sheduled_data
		JSONObject sheduledJson= new JSONObject();
		sheduledJson.put("url", "/returns/getCrnListforDrc01SaveDrc01");
		JSONObject saveUrlData = returnService.saveSheduledServiceDrc01(sheduledJson);
		return "";
	}
	
	@Synchronized
	@Transactional
	public void Drc01DataFunction(String decodeBase64StringToString,String paramsjson) throws Exception {
		try {			
			if(decodeBase64StringToString != null) {
				JSONObject jsondata = new JSONObject(decodeBase64StringToString);
				JSONArray jsonArray = jsondata.getJSONArray("crnlist");
				for(int j=0;j<jsonArray.length();j++) {
					JSONObject jsonObject = jsonArray.getJSONObject(j);
					
					String crnNo = jsonObject.getString("crn");
					System.out.println("Crns2--"+crnNo);
					
					
					//AD1810230079390
					StringBuffer urlString= new StringBuffer();
					urlString.append(UtilsConstant.commonUrl);
					urlString.append("v1.0/adj/m2?");
					urlString.append("state_cd=18&crn="+crnNo+"&action=GETCASEDATAASSMT");
					//After Getting Response In StringOfJSON It well convert into Model 
					String drc01Itemsvpappdata = commonMethodes.getDecreptedUrlDataByUrl(urlString);
//					System.out.println("dataDrc012---"+drc01Itemsvpappdata);
					if(drc01Itemsvpappdata!="0") {
						Drc01Main drc01Data = objectMapper.readValue(drc01Itemsvpappdata, Drc01Main.class);
						drc01Data.path_params=paramsjson;
						//Saving Drc01
						Drc01Main save = drc01MainRepo.save(drc01Data);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	
//	@Synchronized
//	@Transactional
//	@RequestMapping("/dataDrc01ByCrn")
	public void Drc01DataFunctionDataByCrn(String decodeBase64StringToString) throws Exception {
//		if(decodeBase64StringToString != null) {
//
//			JSONObject jsondata = new JSONObject(decodeBase64StringToString);
//			JSONArray jsonArray = jsondata.getJSONArray("crnlist");
//			for(int j=0;j<jsonArray.length();j++) {
//				JSONObject jsonObject = jsonArray.getJSONObject(j);
//
		        List<String> crnListData= new ArrayList<>();
		        crnListData.add("AD180923014437Y");
		        crnListData.add("AD1809230062174");
		        crnListData.add("AD180923013976N");
		        crnListData.add("AD1809230209007");
		        crnListData.add("AD1809230144641");
		        crnListData.add("AD180923018994K");
		        crnListData.add("AD180923020378W");
		        crnListData.add("AD180923012508Z");
		        crnListData.add("AD1810230028959");
		        crnListData.add("AD180923008697M");
		        crnListData.add("AD1809230143180");
		        crnListData.add("AD181123006095J");
		        crnListData.add("AD180923012758Q");
		        crnListData.add("AD1809230202770");
		        crnListData.add("AD1809230292234");
		        crnListData.add("AD1809230092832");
		        crnListData.add("AD180923015905U");
		        crnListData.add("AD180923026629N");
		        crnListData.add("AD180923026069V");
		        crnListData.add("AD180923021115B");
		        crnListData.add("AD180923008556U");
		        crnListData.add("AD180923020694W");
		        crnListData.add("AD1809230173525");
		        crnListData.add("AD180923009130F");
		        crnListData.add("AD181023002925E");
		        crnListData.add("AD180923021377X");
		        crnListData.add("AD180923014396W");
		        crnListData.add("AD1809230115048");
		        crnListData.add("AD180923009563Y");
		        crnListData.add("AD180923018891S");
		        crnListData.add("AD180923022495W");
		        crnListData.add("AD180923011113H");
		        crnListData.add("AD180923008497Q");
		        crnListData.add("AD180923015962U");
		        crnListData.add("AD1809230155036");
		        crnListData.add("AD180923019798F");
		        crnListData.add("AD1809230106617");
		        crnListData.add("AD1809230126318");
		        crnListData.add("AD1809230202853");
		        crnListData.add("AD1809230142174");
		        crnListData.add("AD1809230214808");
		        crnListData.add("AD1809230204726");
		        crnListData.add("AD1809230292028");
		        crnListData.add("AD180923014895O");
		        
		        crnListData.add("AD180923007791X");
		        crnListData.add("AD1809230160762");
		        crnListData.add("AD1809230131193");
		        crnListData.add("AD180923014987J");
		        crnListData.add("AD180923020121J");
		        crnListData.add("AD180923014779K");
		        crnListData.add("AD180923008288T");
		        crnListData.add("AD180923016110I");
		        crnListData.add("AD180923010450E");
		        crnListData.add("AD180923022906T");
		        crnListData.add("AD1809230202473");
		        crnListData.add("AD180923015465Y");
		        crnListData.add("AD180923007398R");
		        crnListData.add("AD1809230067702");
		        crnListData.add("AD1809230096610");
		        crnListData.add("AD180923029166W");
		        crnListData.add("AD180923013738R");
		        crnListData.add("AD180923014788L");
		        crnListData.add("AD180923016478Q");
		        crnListData.add("AD180923016439S");
		        crnListData.add("AD180923019288Q");
		        crnListData.add("AD180923011024G");
		        
		        crnListData.add("AD180923011234B");
		        crnListData.add("AD1809230064047");
		        crnListData.add("AD180923015745U");
		        crnListData.add("AD1810230029288");
		        crnListData.add("AD1809230255604");
		        crnListData.add("AD180923014546X");
		
		        
		        
		        for(int i=0;i<crnListData.size();i++) {
		        	String crnNo =crnListData.get(i);
		        	
		        	//AD1810230079390
		        	StringBuffer urlString= new StringBuffer();
		        	urlString.append(UtilsConstant.commonUrl);
		        	urlString.append("v1.0/adj/m2?");
		        	urlString.append("state_cd=18&crn="+crnNo+"&action=GETCASEDATAASSMT");
		        	//After Getting Response In StringOfJSON It well convert into Model 
		        	String drc01Itemsvpappdata = commonMethodes.getDecreptedUrlDataByUrl(urlString);
		        	System.out.println("dataDrc012---"+drc01Itemsvpappdata);
		        	if(drc01Itemsvpappdata!="0") {
		        		System.out.println("11Drc01Data --"+drc01Itemsvpappdata);
		        		Drc01Main drc01Itemsvpappdatas = objectMapper.readValue(drc01Itemsvpappdata, Drc01Main.class);
		        		
		        		//Saving Drc03
		        		Drc01Main save = drc01MainRepo.save(drc01Itemsvpappdatas);
		        	}
		        }
		        
				System.out.println(crnListData.size()+"---size");

		
		//  }
		//}
	}
	
	
	@Synchronized
	@GetMapping(value = "/saveDrc07DateWise")
	@Transactional
	public void DateList() throws Exception {
//		List<String> dateRange = dateListCommonMethods.getDateRangeInReverseOutput("2023-12-02", "2024-01-01");
		List<String> dateRange = dateListCommonMethods.getDateRange("2024-03-15", "2024-04-16");
		System.out.println(dateRange+"--dateRange");
		JSONArray arr= new JSONArray();
		System.out.println(dateRange);
		for(int i=0;i<dateRange.size();i++) {
			generateDRC07ListForItemIDSaveDrc07DataByDemaindId(dateRange.get(i));
//			JSONArray generateCRNlistForDrc01SaveDrc01DataFor = generateCRNlistForDrc01SaveDrc01DataFor(dateRange.get(i),true);
//			arr.put(generateCRNlistForDrc01SaveDrc01DataFor);
			//man();
//			   String preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateWithoutSec(dateRange.get(i));		
//			   System.out.println(preveousDayofGivenDate+"--preveousDayofGivenDate");
//				List<String> twentyFourHrsLoopForPericularDate = commonMethodes.twentyFourHrsLoopForPericularDate(preveousDayofGivenDate);
//				System.out.println(twentyFourHrsLoopForPericularDate+"--twentyFourHrsLoopForPericularDate");

		}	
		System.out.println("arr--"+arr);
	}
	
	//For Drc07
	@Synchronized
	@GetMapping(value = "/getDrc07ListSaveDrc07ByDemaindId")
	public String generateDRC07ListForItemIDSaveDrc07DataByDemaindId(String sheduledDate) throws Exception {
		try {
				//String preveousDayofGivenDate = commonMethodes.getPreveousDayofGivenDateInStratFormate(sheduledDate);
			String preveousDayofGivenDate = sheduledDate;
			System.out.println("Date----"+preveousDayofGivenDate);
			String generateAuthToken = commonMethodes.generateAuthToken();
			JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);
			System.out.println(generateAuthToken+"---generateAuthToken1");
			System.out.println(tokenJsonResponse+"---generateAuthToken1");
		
			final String response = commonMethodes.getClient().resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v1.0/adj/m2?state_cd=18&date="+preveousDayofGivenDate+"&action=GETDEMANDLISTNONCASE").toString())
					.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
					.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
					.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
					.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
			
			JSONObject json=new JSONObject(response);
			JSONObject paramsjson= new JSONObject();
			paramsjson.put("demainDate", preveousDayofGivenDate);
			if(json.getString("status_cd").equals("1")) {
				String decodeBase64StringToString = commonMethodes.decodeBase64StringToString(new JSONObject(response).optString("data"));
				Drc07DataInsertByDemaindId(decodeBase64StringToString,paramsjson.toString());
				commonMethodes.getClient().destroy();
			}else {
				System.out.println("Error Response----"+json);
			}
			DailyReportDataModel dailyData=new DailyReportDataModel();
		    dailyReportService.dailyreport("Drc07", paramsjson.toString(),preveousDayofGivenDate);
	}catch(Exception e) {
	    e.printStackTrace();
	}
	//			
	//Update  sheduled Date and Url Name Table Name :common_sheduled_data
	JSONObject sheduledJson= new JSONObject();
	sheduledJson.put("url", "/returns/getDrc07ListSaveDrc07ByDemaindId");
	JSONObject saveUrlData = returnService.saveSheduledServiceDrc07(sheduledJson);
	return "";
	}
		
		@Synchronized
		@Transactional
		public void Drc07DataInsertByDemaindId(String decodeBase64StringToString, String paramsData) throws Exception {
			try {
				if(decodeBase64StringToString != null) {
					JSONObject jsondata = new JSONObject(decodeBase64StringToString);
					JSONArray jsonArray = jsondata.getJSONArray("demandList");
					for(int j=0;j<jsonArray.length();j++) {
						JSONObject jsonObject = jsonArray.getJSONObject(j);
						String demandid = jsonObject.getString("demandid");
						System.out.println("demandid--"+demandid);
						//AD1810230079390
						StringBuffer urlString= new StringBuffer();
						urlString.append(UtilsConstant.commonUrl);
						urlString.append("v1.0/adj/m2?");
						urlString.append("state_cd=18&demand_id="+demandid+"&action=GETDEMANDDTLSNONCASE");
						
						//After Getting Response In StringOfJSON It well convert into Model 
						String drc07DataByDemaindId = commonMethodes.getDecreptedUrlDataByUrl(urlString);
						System.out.println("dataDrc012---"+drc07DataByDemaindId);
						if(drc07DataByDemaindId!="0") {
							System.out.println("11Drc01Data --"+drc07DataByDemaindId);
							Drc07Main drc07ModelData = objectMapper.readValue(drc07DataByDemaindId, Drc07Main.class);
							drc07ModelData.path_params = paramsData;
							//Saving Drc03
						     Drc07Main save = drc07MainRepo.save(drc07ModelData);
						}
				  }
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	
		@PostMapping("/llop")
		public void mainData(@RequestBody ManData man) {
			System.out.println("EnteringData");
			System.out.println(man.getMessage());
			System.out.println(man.toString());
		}
		
		
		
}
