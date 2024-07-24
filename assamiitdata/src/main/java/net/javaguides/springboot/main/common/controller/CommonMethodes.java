package net.javaguides.springboot.main.common.controller;
import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.core.MediaType;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import lombok.Synchronized;
import net.javaguides.springboot.main.common.LoginRepository.SheduledRepository;
import net.javaguides.springboot.main.common.service.SheduledService;
import net.javaguides.springboot.main.utils.UtilsConstant;

@Component
public class CommonMethodes {
	
	@Autowired
	SheduledService sheduledService;
	
	@Autowired
	SheduledRepository repo;
	
	public String sdKey = "DEFAULT_VALUE";
	
		Client client = Client.create(new DefaultClientConfig());
	
		public Client getClient() {		  
		  Client client= Client.create(new DefaultClientConfig());
		  return client;
		}
	    public String generateSecureKey() throws Exception {
			    KeyGenerator KEYGEN = KeyGenerator.getInstance("AES");
				KEYGEN.init(256);
				System.out.println(KEYGEN.generateKey().getEncoded()+"---KeyGen");
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
			
			final byte[] decodedKey = Base64.decodeBase64(UtilsConstant.publicKey);
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
//			System.out.println("password--  "+encryptwithPK_PEM("Assam@2023".getBytes()));	
//			System.out.println("appKey--  "+encryptwithPK_PEM(decodeBase64StringTOByte(appkey)));
			final JSONObject json = new JSONObject();
			json.put("action", "ACCESSTOKEN");
			json.put("username", UtilsConstant.userName);
			json.put("password", encryptwithPK_PEM(UtilsConstant.password.getBytes()));
			json.put("app_key", encryptwithPK_PEM(decodeBase64StringTOByte(appkey)));
			final String response = client.resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v0.2/authenticate").toString())
					.accept(MediaType.APPLICATION_JSON).header("state-cd", UtilsConstant.stateCd)
					.header("client-secret", UtilsConstant.clientSecret).header("clientid", UtilsConstant.clientId)
					.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json.toString()).getEntity(String.class);
		//	System.out.println("Response Data :: " + response);
			client.destroy();
			return response;
		}
		
		@Synchronized
		public String getDecreptedUrlDataByUrl(StringBuffer Url) throws Exception {
			String generateAuthToken = generateAuthToken();
			JSONObject tokenJsonResponse=new JSONObject(generateAuthToken);

			final String response = getClient().resource(Url.toString())
					.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
					.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
					.header("username", "GSTG2G18").header("auth-token", tokenJsonResponse.getString("auth_token"))
					.type(MediaType.APPLICATION_JSON).get(ClientResponse.class).getEntity(String.class);
			
			JSONObject json= new JSONObject(response);
			if(json.getString("status_cd").equals("1")) {				
				String decodeBase64StringToString = decodeBase64StringToString(new JSONObject(response).getString("data"));
				System.out.println("Response Data CRNLST:: " + decodeBase64StringToString);
				getClient().destroy();
				return decodeBase64StringToString;
			}else {
				return "0";
			}
		}


      //24 Hrs -- Time Loop for a perticular Date starts From 2023-10-11 to 2023-10-11:23:00
		public List<String> twentyFourHrsLoopForPericularDate(String Date) throws Exception{
			String dateString = Date;
	        // Define the format for parsing the date string
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	        // Parse the date string into a LocalDate object
	        LocalDate date = LocalDate.parse(dateString, formatter);

	        // Create a list to store the formatted times
	        List<String> listOfTime = new ArrayList<>();

	        // Define the format for printing the time
	        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm");

	        // Loop through the 24 hours of the day
	        for (int hour = 0; hour < 24; hour++) {
	            // Create a LocalDateTime object for each hour of the day
	            LocalDateTime time = LocalDateTime.of(date, LocalTime.of(hour, 0));
	            listOfTime.add(time.format(formatter2));
	        }
	        // Print the list of formatted times
	        System.out.println(listOfTime);
			return listOfTime;
	        
		}

		//Input DateAndTime	Input Formate 2024-03-04 10:56:10      
		//OutPut Date(Reverse Formate)     2024-03-07
		public String getPreveousDayofGivenDate(String DateAndTime) {
			 String dateTimeString = DateAndTime;
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		     LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
		     // Get the previous day
		     LocalDate previousDay = dateTime.toLocalDate().minusDays(1);
			return previousDay.toString();
		}
		
		// Input DateAndTime (Input Formate)  2024-03-04      
		// OutPut Date(Reverse Formate)       2024-03-03
		public String getPreveousDayofGivenDateWithoutSec(String DateAndTime) {
		     LocalDate date = LocalDate.parse(DateAndTime);
	         // Get the previous day
	         LocalDate previousDay = date.minusDays(1);
			return previousDay.toString();
		}
		
		//Input DateAndTime	Input Formate 2024-03-04 10:56:10      
		//OutPut Date(Reverse Formate)     07-03-2024
		public String getPreveousDayofGivenDateInStratFormate(String DateAndTime) {
			 //String dateTimeString = DateAndTime;
			 String dateTimeString = DateAndTime; // Example date and time string
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
		        // Get the previous day
		        LocalDate previousDay = dateTime.toLocalDate().minusDays(1);
		        // Format the previous day as "dd/MM/yyyy"
		        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		        String formattedPreviousDay = previousDay.format(outputFormatter);
                return formattedPreviousDay;
		}
		
		
}
