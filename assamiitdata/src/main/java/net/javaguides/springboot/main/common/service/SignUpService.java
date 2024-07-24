package net.javaguides.springboot.main.common.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.main.common.LoginRepository.LoginRepository;
import net.javaguides.springboot.main.common.LoginRepository.SignUpRepository;
import net.javaguides.springboot.main.common.model.LoginData;
import net.javaguides.springboot.main.common.model.SignUpData;
import net.javaguides.springboot.main.common.serviceI.SignUpServiceI;

@Service
public class SignUpService implements SignUpServiceI {

	@Autowired
	LoginRepository loginRepo;
	
	@Autowired
	SignUpRepository signUpRepo;
	

	@Override
	public String LoginService(JSONObject json) throws JSONException {
		
		String userName=json.getString("userName");
		String password=json.getString("userPassword");
        JSONObject responseJson=new JSONObject();
        
		try {
			LoginData findByUserName = loginRepo.findByUserName(userName);
			if(findByUserName == null) {
				responseJson.put("status","1004a");
			}else {
				LoginData findByPassword = loginRepo.findByPassword(password);
				if(findByPassword == null) {
					responseJson.put("status","1004b");
				}else {
					responseJson.put("status","1005");
				}
			}
		}catch(Exception e) {
			System.out.println("1010 Error Login");
		}
		return responseJson.toString();
	}


	@Override
	public String SignUpService(JSONObject json) throws JSONException {
		System.out.println("Entering");
		String name=json.getString("name");
		String userName=json.getString("userName");
		String password=json.getString("userPassword");
		
        JSONObject responseJson=new JSONObject();
		try {

			LoginData data =new LoginData();
			data.name=name;
			data.userName=userName;
			data.password=password;

			LoginData findByUserName = loginRepo.findByUserName(userName);

			if(findByUserName == null) {
				loginRepo.save(data);
				responseJson.put("status", "1005");
			}else {
				responseJson.put("status", "1004a");
			}

		}catch(Exception e) {
			System.out.println("1010 Error signup");
		}
 		return responseJson.toString();
	}
	

}
