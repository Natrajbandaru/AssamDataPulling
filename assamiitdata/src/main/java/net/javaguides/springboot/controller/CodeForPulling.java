package net.javaguides.springboot.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.core.MediaType;

import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.crypto.util.SubjectPublicKeyInfoFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

//import org.apache.tomcat.util.codec.binary.Base64;

@RestController
public class CodeForPulling {
	
	 Client client= Client.create(new DefaultClientConfig());

	 private final RestTemplate restTemplate;

	   @Autowired
	    public CodeForPulling(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }
	
	
	 private static final String publicKey =
             "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgT9WDfNxyzEW3rWzR2Me6mqUl0zZSEfyKCE/rEuBPwHSUU40o2INl8CPQFv20g8NJ2sUru+RDyZRO7/X60JDij/ia3DZELkLJqozpS1GwK4U072l31lVLfZOzGC54q34EU3MQMt1KXdaVUZCC3tCEDAOpI+dI1Uw3VXI0E8HSQbASPhmDd3ZYQ/lYEvlkfum7/D3cAE7oUJQVNDksxixc6veWXRZQmTtEdBcUuQv2rf7V2XcWYnOgs68nbLTLAKzLLyb4oE+wQXHoqFafODIhbapkHd6Xi7rB19LFHIsM9US1Zpa6CMIxU7jsdEhLJ75Pn/Se/PFe521NMJYtdm/HQIDAQAB";

	
	 //App Key Generation
	 public static String generateSecureKey() throws Exception {
	        KeyGenerator   KEYGEN = KeyGenerator.getInstance("AES");
			KEYGEN.init(256);
			return encodeBase64String(KEYGEN.generateKey().getEncoded());
	}
	  public static String encodeBase64String(byte[] bytes) {
			return new String(java.util.Base64.getEncoder().encode(bytes));
	 }

//		public String encrypt(byte[] planTextToEncrypt)
//				throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
//				InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
////			final Path keyPath = Paths.get(System.getProperty("user.dir") + "//" + "gstnpublic_1.pem");
//	//
////			final String keyContent = new String(Files.readAllBytes(keyPath)).replace("-----BEGIN RSA PUBLIC KEY-----", "");
////			final String modifiedKeyContent = keyContent.replace("-----END RSA PUBLIC KEY-----", "");
//			// System.out.println(System.getProperty("user.dir") + "//" +
//			// PUBLIC_KEY_PATH_PEM);
//			final byte[] decodedKey = Base64.decodeBase64("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgT9WDfNxyzEW3rWzR2Me6mqUl0zZSEfyKCE/rEuBPwHSUU40o2INl8CPQFv20g8NJ2sUru+RDyZRO7/X60JDij/ia3DZELkLJqozpS1GwK4U072l31lVLfZOzGC54q34EU3MQMt1KXdaVUZCC3tCEDAOpI+dI1Uw3VXI0E8HSQbASPhmDd3ZYQ/lYEvlkfum7/D3cAE7oUJQVNDksxixc6veWXRZQmTtEdBcUuQv2rf7V2XcWYnOgs68nbLTLAKzLLyb4oE+wQXHoqFafODIhbapkHd6Xi7rB19LFHIsM9US1Zpa6CMIxU7jsdEhLJ75Pn/Se/PFe521NMJYtdm/HQIDAQAB");
//			final X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedKey);
//			final KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//			final PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
//			final Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//			final byte[] encryptedByte = cipher.doFinal(planTextToEncrypt);
//			return new String(java.util.Base64.getEncoder().encode(encryptedByte));
//
//		}
	//Decode
    public static byte[] decodeBase64StringTOByte(String stringData) throws Exception {
		return java.util.Base64.getDecoder().decode(stringData.getBytes("UTF-8"));
	}
    
    public static String decodeBase64StringToString(String stringData) throws Exception {
        byte[] decodedBytes = Base64.getDecoder().decode(stringData.getBytes(StandardCharsets.UTF_8));
        return new String(decodedBytes, StandardCharsets.UTF_8);
    }
    
//    // Decodeing Data with app_key and password with public key
    public static String encrypt(String data) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", new BouncyCastleProvider());

            byte[] keyBytes = Base64.getDecoder().decode(publicKey);
            AsymmetricKeyParameter asymmetricKeyParameter = PublicKeyFactory.createKey(keyBytes);
            RSAKeyParameters rsaKeyParameters = (RSAKeyParameters) asymmetricKeyParameter;

            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(rsaKeyParameters).getEncoded()));

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, rsaPublicKey);

            byte[] plaintext = data.getBytes("UTF-8");
            byte[] ciphertext = cipher.doFinal(plaintext);

            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }	    
    
    //----
    
//	public String getDataWithHeaders(String url, HttpHeaders headers,String requestBody) {
//
//		HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.addAll(headers);
//		//headers.setContentType(MediaType.APPLICATION_JSON);
//	    HttpEntity<String> entity = new HttpEntity<>(requestBody,requestHeaders);
//	
//	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//	    String responseBody = response.getBody();
//
//        return responseBody;
//    }
//	
	
	
//	@RequestMapping("/testdata")
//	public String controllerWebtest() throws Exception {
//		RestTemplate restTemplate = new RestTemplate();
//        // Create HttpHeaders and add custom headers
//        System.out.println("ppp1");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("state-cd", "18");
//        headers.add("client-secret", "61890766fb17402dbcffd712abe0c886");
//        headers.add("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a");
//        String password = encrypt("Assam@2023".getBytes());
//        System.out.println(password+"   --password");
//        String appkey = encrypt(generateSecureKey().getBytes());
//        System.out.println(appkey+"   --appkey");
//
//        
//        
//        JSONObject json= new JSONObject();
//        json.put("action", "ACCESSTOKEN");
//        json.put("username", "GSTG2G18");
//        json.put("password", encrypt("Assam@2023".getBytes()));
//        json.put("app_key", encrypt(generateSecureKey().getBytes()));
//
//        Testing myRestService = new Testing(restTemplate);
//        String requestBody = json.toString();
//        
////        String requestBody = "{\"action\":\"ACCESSTOKEN\",\"username\":\"GSTG2G18\",\"password\":\"MVJWRzVgraV0qFzACcSf/UIGi43dpUINCu+yb/AkmIY9OGRB8/SDSaY1asckLVIYOfz4UcGo2CrRdIiTbG6Yj6BhzQjk3MIrE8zpqpLsUL+9VgvNscoEj1blMHSAu1n8m4H4RxZnXVmWNXwjuEpDhMGxvZex6sMYlVSk/CufEOw0knWogXOtFLBHnNTkgewUYc0YSoP6hcK7S5gmd4cqVW6xRgccJvw1laRXSPSFmqgfXmRwj1VTx27RdniZWzjSuAes9zG9/kCuKNKedURt/QO1U14Tw0+/uizf3TKpmirpG8lpE0bbZJ4gR7uNkZLuWjVAY+ZOlVxy6AEmXjvjiw==\",\"app_key\":\"A02vLMtdtDPwHUmA5kgXqpLp8VhNCJuLUMCR609jFdQrS1tlB3sGHT3s6LqywCVPqEzsIpi/RvwbLQwnD6LT4i53OqxirjdSgLRfnwxMSWFV4ZxxMiG6sGpHlZguggd3PUumOtRZTsB/xId6uQFpftb/ZOq/X69Mx04k4o+uyc5PJUmWT6W82gsfa5va8+croeRcon88Vimx8E+vAzHh80DxmPDxfRDeDxQyC9pPNlRM9cv4iVEU2IWkOJGoR67WWCCdOFRD+4DRofbDCNLzOZT4PThENMB4PyBzDT28/vx5gEUFydWyjl/uWAmxESwJbtFkwQB7ITha7/XKUFZ6Bw==\"}";
//
//        // Define the URL
//        String url = "https://boapi.internal.gst.gov.in/govtapi/v0.2/authenticate";
//        System.out.println("ppp");
//        // Make the request with custom headers
//        String dataWithHeaders = myRestService.getDataWithHeaders(url, headers,requestBody);
//        System.out.println(dataWithHeaders+"--dataWithHeaders");
//		return  dataWithHeaders;
//	}
    
    
	public  String generateAuthToken() throws Exception {
	//	System.out.println("password--  "+encrypt("Assam@2023".getBytes()));

		
		String appkey = generateSecureKey();
		System.out.println("appKey--  "+decodeBase64StringTOByte(appkey));

		final JSONObject json = new JSONObject();
		json.put("action", "ACCESSTOKEN");
		json.put("username", "GSTG2G18");
		json.put("password", encrypt("Assam@2023"));
		json.put("app_key", encrypt(appkey));
//		json.put("password", encrypt("Assam@2023".getBytes()));
//		json.put("app_key", encrypt(decodeBase64StringTOByte(appkey)));

		final String response = client.resource(new StringBuffer("https://boapi.internal.gst.gov.in/govtapi/").append("v0.2/authenticate").toString())
				.accept(MediaType.APPLICATION_JSON).header("state-cd", "18")
				.header("client-secret", "61890766fb17402dbcffd712abe0c886").header("clientid", "l7xx9fcff7c8c2b749ceab52a0583fcb801a")
				.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, json.toString()).getEntity(String.class);
		System.out.println("Response Data :: " + response);
		client.destroy();
		return response;
	}
	
	@RequestMapping("/getDatas")
	public String mainData() throws Exception {
		String generateAuthToken = generateAuthToken();
		System.out.println(generateAuthToken+"--generateAuthToken");
		return generateAuthToken;
	}
	
	//decodeBase64StringToString
	
	@RequestMapping("/getdatastring")
	public String mainDataa() throws Exception {
		String generateAuthToken = "eyJzdGF0dXNfZGVzYyI6IkFja25vd2xlZGdlZCIsImNhc2V0eSI6IkFESlZQIiwiZG9mIjoiMjAyMC0xMi0zMSIsImdzdGluIjoiMThBQUNDTjgzODRSMVpHIiwic3RhdGVfY2QiOiIxOCIsIml0ZW1zIjpbeyJ2cGFwcGRhdGEiOnsidnAiOnsicHlzdW0iOnsicGF5bWVudGRhdGUiOiIzMS8xMi8yMDIwIiwicnNuIjoiSU5URVJFU1QgJiBUQVggUEFZTUVOVCBGT1IgRi5ZIDE4LTE5IChBTk5VQUwgUkVUVVJOKSIsInNjbmR0IjoiIiwicHJuIjoiSVAxODEyMjAwMDAxNTg0IiwibGJsdHlkdGxzIjp7ImFjdCI6W3siaW50ciI6IjQzNjkiLCJ0b3RhbCI6IjQzNjkiLCJ0eCI6IjAiLCJwb3MiOiIxOSIsImFjdHR5cCI6IklHU1QiLCJkYnRubyI6IkRDMTgxMjIwMDA5Mjc5MyIsImRidGR0IjoiMjAyMC0xMi0zMSIsInRwIjp7ImZyb21tIjoiQVBSIiwidG9tIjoiTUFSIiwiZnJvbXkiOiIyMDE4IiwidG95IjoiMjAxOSJ9LCJwbmx0eSI6IjAiLCJvdGhlcnMiOiIwIiwibGRncnV0IjoiQ2FzaCJ9LHsiaW50ciI6IjExNzQiLCJ0b3RhbCI6IjI4MjciLCJ0eCI6IjE2NTMiLCJwb3MiOiIiLCJhY3R0eXAiOiJDR1NUIiwiZGJ0bm8iOiJEQzE4MTIyMDAwOTI3OTMiLCJkYnRkdCI6IjIwMjAtMTItMzEiLCJ0cCI6eyJmcm9tbSI6IkFQUiIsInRvbSI6Ik1BUiIsImZyb215IjoiMjAxOCIsInRveSI6IjIwMTkifSwicG5sdHkiOiIwIiwib3RoZXJzIjoiMCIsImxkZ3J1dCI6IkNhc2gifSx7ImludHIiOiIxMTc0IiwidG90YWwiOiIyODI3IiwidHgiOiIxNjUzIiwicG9zIjoiIiwiYWN0dHlwIjoiU0dTVCIsImRidG5vIjoiREMxODEyMjAwMDkyNzkzIiwiZGJ0ZHQiOiIyMDIwLTEyLTMxIiwidHAiOnsiZnJvbW0iOiJBUFIiLCJ0b20iOiJNQVIiLCJmcm9teSI6IjIwMTgiLCJ0b3kiOiIyMDE5In0sInBubHR5IjoiMCIsIm90aGVycyI6IjAiLCJsZGdydXQiOiJDYXNoIn1dfSwiZGN1cGR0bHMiOlt7fV0sInNlYyI6Ijc0KDUpIiwiY3MiOiJWb2x1bnRhcnkiLCJkZWNkdGxzIjp7ImR0IjoiMzEvMTIvMjAyMCIsInNpZ250eSI6IiIsImFzZGVzIjoiRElSRUNUT1IiLCJwbCI6IlNJTENIQVIiLCJhc25tIjoiU1VNQVRJIE5BSEFUQSIsInBhbiI6IkFBRFBOMjI1N1IifSwib3Roc2VjIjoiIiwiZnkiOiIyMDE4LTIwMTkiLCJvdnRwIjp7ImZyb21tb250aCI6IkFQUiIsInRvbW9udGgiOiJNQVIiLCJmcm9teWVhciI6IjIwMTgiLCJ0b3llYXIiOiIyMDE5In0sIm1lIjoiMjQwMzM5ODkiLCJzY25ubyI6IiJ9fX0sIml0ZW1uYW1lIjoiQVBQTElDQVRJT04iLCJyZWZpZCI6IiJ9LHsidnBhY2siOlt7InZwYWNrZGF0YSI6eyJyZWFzb24iOiIiLCJzZHRscyI6eyJhY2tsZ3ZwIjp7Im1haW5kb2NzIjpbeyJkY3VwZHRscyI6eyJjdCI6ImFwcGxpY2F0aW9uL3BkZiIsImRvY05hbWUiOiJEUkNfMDM0NzUwMTYucGRmIiwidHkiOiJBREpPIiwiaWQiOiIyMDIzMDEwOTkyNTIyMjciLCJoYXNoIjoiNzNiZDE0M2EyZDUwZTIyZTRkYjViZjk4Yjg4ODYzNGU5ZDRjOTgxMjkxNmZjYjE4ZTZjZjg5ZjFlNGI5M2JhOCJ9fSx7ImRjdXBkdGxzIjp7ImN0IjoiYXBwbGljYXRpb24vcGRmIiwiZG9jTmFtZSI6IjI5NzM1MkRSQy0wNC5wZGYiLCJ0eSI6IkFESk8iLCJpZCI6IjIwMjMwMTA5OTI1MjIzMCIsImhhc2giOiIyMTc3NWE3NWY2NGZiYjExNDA3NWU5YjAxMzRlZDQwYjAwMTY2OWIwNTkxNzAxNDQ5N2EyMmQ5ODQ5Zjg0MTA1In19XSwic3VwcGRvY3MiOltdLCJhbm54ZG9jcyI6W3siZGN1cGR0bHMiOnsiY3QiOiJhcHBsaWNhdGlvbi9wZGYiLCJkb2NOYW1lIjoiRFJDXzAzNDc1MDE2IC0gMi5wZGYiLCJ0eSI6IkFESk8iLCJkb2N0dGwiOiJUUF9TaGFyZV9Xb3Jrc2hlZXRzIiwiaWQiOiIyMDIzMDEwOTkyNTIyMjgiLCJoYXNoIjoiNzNiZDE0M2EyZDUwZTIyZTRkYjViZjk4Yjg4ODYzNGU5ZDRjOTgxMjkxNmZjYjE4ZTZjZjg5ZjFlNGI5M2JhOCJ9fSx7ImRjdXBkdGxzIjp7ImN0IjoiYXBwbGljYXRpb24vcGRmIiwiZG9jTmFtZSI6IkRSQ18wMzQ3NTAxNiAtIDMucGRmIiwidHkiOiJBREpPIiwiZG9jdHRsIjoiVFBfVXBsb2FkZWRfV29ya3NoZWV0cyIsImlkIjoiMjAyMzAxMDk5MjUyMjI5IiwiaGFzaCI6IjczYmQxNDNhMmQ1MGUyMmU0ZGI1YmY5OGI4ODg2MzRlOWQ0Yzk4MTI5MTZmY2IxOGU2Y2Y4OWYxZTRiOTNiYTgifX1dLCJ0eXBlIjoiSVNTVUUgQUNLTk9XTEVER0VNRU5UIn19LCJ0b2R0bHMiOnsiZHQiOiIxOS8wNy8yMDIzIiwidG9pZCI6IjEwMDI5MTExIiwiZGciOiJTdXBlcmludGVuZGVudCIsInNpZ250eSI6IkRTQyIsInBsIjoiU0lMQ0hBUiBJIFJBTkdFIiwicG4iOiIiLCJubSI6IlBhcnRoYSBQcmF0aW0gRGFzIn19LCJpdGVtbmFtZSI6IklTU1VFIEFDS05PV0xFREdFTUVOVCIsInJlZmR0IjoiMTkvMDcvMjAyMyIsInJlZmlkIjoiWk8xODA3MjMwMjA0NzU1In1dfV0sImNybiI6IkFEMTgxMjIwMDAxODMzTSJ9";
		System.out.println(generateAuthToken+"--generateAuthToken");
		
		String decodeBase64StringToString = decodeBase64StringToString(generateAuthToken);
		return decodeBase64StringToString;
	}
	
    // eyJzdGF0dXNfZGVzYyI6IkFja25vd2xlZGdlZCIsImNhc2V0eSI6IkFESlZQIiwiZG9mIjoiMjAyMC0xMi0zMSIsImdzdGluIjoiMThBQUNDTjgzODRSMVpHIiwic3RhdGVfY2QiOiIxOCIsIml0ZW1zIjpbeyJ2cGFwcGRhdGEiOnsidnAiOnsicHlzdW0iOnsicGF5bWVudGRhdGUiOiIzMS8xMi8yMDIwIiwicnNuIjoiSU5URVJFU1QgJiBUQVggUEFZTUVOVCBGT1IgRi5ZIDE4LTE5IChBTk5VQUwgUkVUVVJOKSIsInNjbmR0IjoiIiwicHJuIjoiSVAxODEyMjAwMDAxNTg0IiwibGJsdHlkdGxzIjp7ImFjdCI6W3siaW50ciI6IjQzNjkiLCJ0b3RhbCI6IjQzNjkiLCJ0eCI6IjAiLCJwb3MiOiIxOSIsImFjdHR5cCI6IklHU1QiLCJkYnRubyI6IkRDMTgxMjIwMDA5Mjc5MyIsImRidGR0IjoiMjAyMC0xMi0zMSIsInRwIjp7ImZyb21tIjoiQVBSIiwidG9tIjoiTUFSIiwiZnJvbXkiOiIyMDE4IiwidG95IjoiMjAxOSJ9LCJwbmx0eSI6IjAiLCJvdGhlcnMiOiIwIiwibGRncnV0IjoiQ2FzaCJ9LHsiaW50ciI6IjExNzQiLCJ0b3RhbCI6IjI4MjciLCJ0eCI6IjE2NTMiLCJwb3MiOiIiLCJhY3R0eXAiOiJDR1NUIiwiZGJ0bm8iOiJEQzE4MTIyMDAwOTI3OTMiLCJkYnRkdCI6IjIwMjAtMTItMzEiLCJ0cCI6eyJmcm9tbSI6IkFQUiIsInRvbSI6Ik1BUiIsImZyb215IjoiMjAxOCIsInRveSI6IjIwMTkifSwicG5sdHkiOiIwIiwib3RoZXJzIjoiMCIsImxkZ3J1dCI6IkNhc2gifSx7ImludHIiOiIxMTc0IiwidG90YWwiOiIyODI3IiwidHgiOiIxNjUzIiwicG9zIjoiIiwiYWN0dHlwIjoiU0dTVCIsImRidG5vIjoiREMxODEyMjAwMDkyNzkzIiwiZGJ0ZHQiOiIyMDIwLTEyLTMxIiwidHAiOnsiZnJvbW0iOiJBUFIiLCJ0b20iOiJNQVIiLCJmcm9teSI6IjIwMTgiLCJ0b3kiOiIyMDE5In0sInBubHR5IjoiMCIsIm90aGVycyI6IjAiLCJsZGdydXQiOiJDYXNoIn1dfSwiZGN1cGR0bHMiOlt7fV0sInNlYyI6Ijc0KDUpIiwiY3MiOiJWb2x1bnRhcnkiLCJkZWNkdGxzIjp7ImR0IjoiMzEvMTIvMjAyMCIsInNpZ250eSI6IiIsImFzZGVzIjoiRElSRUNUT1IiLCJwbCI6IlNJTENIQVIiLCJhc25tIjoiU1VNQVRJIE5BSEFUQSIsInBhbiI6IkFBRFBOMjI1N1IifSwib3Roc2VjIjoiIiwiZnkiOiIyMDE4LTIwMTkiLCJvdnRwIjp7ImZyb21tb250aCI6IkFQUiIsInRvbW9udGgiOiJNQVIiLCJmcm9teWVhciI6IjIwMTgiLCJ0b3llYXIiOiIyMDE5In0sIm1lIjoiMjQwMzM5ODkiLCJzY25ubyI6IiJ9fX0sIml0ZW1uYW1lIjoiQVBQTElDQVRJT04iLCJyZWZpZCI6IiJ9LHsidnBhY2siOlt7InZwYWNrZGF0YSI6eyJyZWFzb24iOiIiLCJzZHRscyI6eyJhY2tsZ3ZwIjp7Im1haW5kb2NzIjpbeyJkY3VwZHRscyI6eyJjdCI6ImFwcGxpY2F0aW9uL3BkZiIsImRvY05hbWUiOiJEUkNfMDM0NzUwMTYucGRmIiwidHkiOiJBREpPIiwiaWQiOiIyMDIzMDEwOTkyNTIyMjciLCJoYXNoIjoiNzNiZDE0M2EyZDUwZTIyZTRkYjViZjk4Yjg4ODYzNGU5ZDRjOTgxMjkxNmZjYjE4ZTZjZjg5ZjFlNGI5M2JhOCJ9fSx7ImRjdXBkdGxzIjp7ImN0IjoiYXBwbGljYXRpb24vcGRmIiwiZG9jTmFtZSI6IjI5NzM1MkRSQy0wNC5wZGYiLCJ0eSI6IkFESk8iLCJpZCI6IjIwMjMwMTA5OTI1MjIzMCIsImhhc2giOiIyMTc3NWE3NWY2NGZiYjExNDA3NWU5YjAxMzRlZDQwYjAwMTY2OWIwNTkxNzAxNDQ5N2EyMmQ5ODQ5Zjg0MTA1In19XSwic3VwcGRvY3MiOltdLCJhbm54ZG9jcyI6W3siZGN1cGR0bHMiOnsiY3QiOiJhcHBsaWNhdGlvbi9wZGYiLCJkb2NOYW1lIjoiRFJDXzAzNDc1MDE2IC0gMi5wZGYiLCJ0eSI6IkFESk8iLCJkb2N0dGwiOiJUUF9TaGFyZV9Xb3Jrc2hlZXRzIiwiaWQiOiIyMDIzMDEwOTkyNTIyMjgiLCJoYXNoIjoiNzNiZDE0M2EyZDUwZTIyZTRkYjViZjk4Yjg4ODYzNGU5ZDRjOTgxMjkxNmZjYjE4ZTZjZjg5ZjFlNGI5M2JhOCJ9fSx7ImRjdXBkdGxzIjp7ImN0IjoiYXBwbGljYXRpb24vcGRmIiwiZG9jTmFtZSI6IkRSQ18wMzQ3NTAxNiAtIDMucGRmIiwidHkiOiJBREpPIiwiZG9jdHRsIjoiVFBfVXBsb2FkZWRfV29ya3NoZWV0cyIsImlkIjoiMjAyMzAxMDk5MjUyMjI5IiwiaGFzaCI6IjczYmQxNDNhMmQ1MGUyMmU0ZGI1YmY5OGI4ODg2MzRlOWQ0Yzk4MTI5MTZmY2IxOGU2Y2Y4OWYxZTRiOTNiYTgifX1dLCJ0eXBlIjoiSVNTVUUgQUNLTk9XTEVER0VNRU5UIn19LCJ0b2R0bHMiOnsiZHQiOiIxOS8wNy8yMDIzIiwidG9pZCI6IjEwMDI5MTExIiwiZGciOiJTdXBlcmludGVuZGVudCIsInNpZ250eSI6IkRTQyIsInBsIjoiU0lMQ0hBUiBJIFJBTkdFIiwicG4iOiIiLCJubSI6IlBhcnRoYSBQcmF0aW0gRGFzIn19LCJpdGVtbmFtZSI6IklTU1VFIEFDS05PV0xFREdFTUVOVCIsInJlZmR0IjoiMTkvMDcvMjAyMyIsInJlZmlkIjoiWk8xODA3MjMwMjA0NzU1In1dfV0sImNybiI6IkFEMTgxMjIwMDAxODMzTSJ9
	
	
}
