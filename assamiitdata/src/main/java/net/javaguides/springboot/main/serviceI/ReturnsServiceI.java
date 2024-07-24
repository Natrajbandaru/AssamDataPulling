package net.javaguides.springboot.main.serviceI;

import org.json.JSONException;
import org.json.JSONObject;

public interface ReturnsServiceI {
	
    public JSONObject saveServiceCrnList(JSONObject sheduledJson) throws JSONException;
    
	public JSONObject saveServiceDrc03(JSONObject sheduledJson) throws JSONException;

}
