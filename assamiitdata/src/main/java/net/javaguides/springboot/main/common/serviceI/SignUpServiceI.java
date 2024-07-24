package net.javaguides.springboot.main.common.serviceI;

import org.json.JSONException;
import org.json.JSONObject;

public interface SignUpServiceI {

	public String LoginService(JSONObject json) throws JSONException;
	
	public String SignUpService(JSONObject json) throws JSONException;


}
