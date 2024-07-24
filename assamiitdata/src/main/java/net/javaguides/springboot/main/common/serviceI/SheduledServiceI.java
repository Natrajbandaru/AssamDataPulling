package net.javaguides.springboot.main.common.serviceI;

import org.json.JSONException;
import org.json.JSONObject;

public interface SheduledServiceI {
	
	public JSONObject saveUrlData(JSONObject json) throws JSONException ;

}
