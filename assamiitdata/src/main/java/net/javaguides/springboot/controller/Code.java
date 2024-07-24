package net.javaguides.springboot.controller;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import net.javaguides.springboot.main.common.controller.CommonMethodes;

import com.sun.jersey.api.client.ClientResponse;

@RestController
public class Code {
	
	@Autowired
	CommonMethodes commeonMethods;
	
    Client client= Client.create(new DefaultClientConfig());

    public String generateSecureKey() throws Exception {
		    KeyGenerator KEYGEN = KeyGenerator.getInstance("AES");
			KEYGEN.init(256);
			return encodeBase64String(KEYGEN.generateKey().getEncoded());
	}
	public static String encodeBase64String(byte[] bytes) {
		return new String(java.util.Base64.getEncoder().encode(bytes));
	}
	//Decode  encodedString to DecodeByte
	public static byte[] decodeBase64StringTOByte(String encodedString) throws Exception {
		return java.util.Base64.getDecoder().decode(encodedString.getBytes("UTF-8"));
	}
	//Decode  encodedString to DecodeString
    public static String decodeBase64StringToString(String encodedString) {
         byte[] decodedBytes =java.util.Base64.getDecoder().decode(encodedString);
         return new String(decodedBytes);
    }
    
//Method For a Encode with a (publicKey with password) and (public Key with a AppKey or sessionKey(Randomly Generated)) 
	public String encryptwithPK_PEM(byte[] planTextToEncrypt)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		final byte[] decodedKey = Base64.decodeBase64("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgT9WDfNxyzEW3rWzR2Me6mqUl0zZSEfyKCE/rEuBPwHSUU40o2INl8CPQFv20g8NJ2sUru+RDyZRO7/X60JDij/ia3DZELkLJqozpS1GwK4U072l31lVLfZOzGC54q34EU3MQMt1KXdaVUZCC3tCEDAOpI+dI1Uw3VXI0E8HSQbASPhmDd3ZYQ/lYEvlkfum7/D3cAE7oUJQVNDksxixc6veWXRZQmTtEdBcUuQv2rf7V2XcWYnOgs68nbLTLAKzLLyb4oE+wQXHoqFafODIhbapkHd6Xi7rB19LFHIsM9US1Zpa6CMIxU7jsdEhLJ75Pn/Se/PFe521NMJYtdm/HQIDAQAB");
		final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedKey);
		final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		final PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
		final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		final byte[] encryptedByte = cipher.doFinal(planTextToEncrypt);
		return new String(java.util.Base64.getEncoder().encode(encryptedByte));

	}
//Method For Generating Token  By Taking EncPasword and EncAppkey with a publicKey 
	public  String generateAuthToken() throws Exception {
		String appkey = generateSecureKey();
		System.out.println("password--  "+encryptwithPK_PEM("Assam@2023".getBytes()));	
		System.out.println("appKey--  "+encryptwithPK_PEM(decodeBase64StringTOByte(appkey)));
		final JSONObject json = new JSONObject();
		json.put("action", "ACCESSTOKEN");
		json.put("username", "GSTG2G18");
		json.put("password", encryptwithPK_PEM("Assam@2023".getBytes()));
		json.put("app_key", encryptwithPK_PEM(decodeBase64StringTOByte(appkey)));
		final String response = client.resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v0.2/authenticate").toString())
				.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
				.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
				.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json.toString()).getEntity(String.class);
		System.out.println("Response Data :: " + response);
		client.destroy();
		return response;
	}
	
	@RequestMapping("/getToken")
	public String mainData() throws Exception {
		String generateAuthToken = commeonMethods.generateAuthToken();
		System.out.println("GenerateAuthToken  -->>> "+generateAuthToken);
		return generateAuthToken;
	}
	
	@PostMapping(value="/getDecode"  ,consumes = { "application/json", "application/x-www-form-urlencoded" }, produces = "application/json")
	public String encodeToDecode(@RequestBody String formData) throws JSONException {
        JSONObject json= new JSONObject(formData);
        String string = json.getString("data");
		String decodeBase64StringToString = commeonMethods.decodeBase64StringToString(string);
		return decodeBase64StringToString;
	}

	
	// -------------- FOR  CRS---
	public  String generateCRNlist() throws Exception {
		String generateAuthToken = generateAuthToken();
		JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);

//		final JSONObject json = new JSONObject();
//		json.put("state_cd", 18);
//		json.put("start_tm", "2022-04-13:09:16");
//		json.put("end_tm", "2023-04-13:10:16");
//		json.put("casetyp", "APPEL");
//		json.put("iseod", "Y");


		final String response = client.resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v1.0/caselst?state_cd=18&start_tm=2023-10-11:11:16&end_tm=2023-10-11:12:16&action=CRNLST&casetyp=ADJVP&iseod=N").toString())
				.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
				.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
				.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
				.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
		
		String decodeBase64StringToString = decodeBase64StringToString(new JSONObject(response).getString("data"));
		System.out.println("Response Data CRNLST:: " + decodeBase64StringToString);
		client.destroy();
		return decodeBase64StringToString;
	}
	
	@RequestMapping("/getcrnlist")
	public String getCrnList() throws Exception {
		String generateCRNlist = generateCRNlist();
		String decodeBase64StringToString = decodeBase64StringToString(new JSONObject(generateCRNlist).getString("data"));
		return decodeBase64StringToString;
	}
	
}
