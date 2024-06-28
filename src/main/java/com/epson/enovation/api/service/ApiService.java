package com.epson.enovation.api.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.epson.enovation.model.APIConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Component
public class ApiService {
	
	//private HashMap<String, Object> aws; 
	
	//public ApiService(String requestURL){
	//	this.aws = get(requestURL);
	//}
	
	@Value("${spring.application.name}")
	private String name;
	
	//@Autowired
	//private APIConfig apiConfig;
	


	public HashMap<String, Object> get(String requestURL)  {
		
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();
		//ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();

            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            //System.out.println("error");
            //System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            //System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}
	
	
public HashMap<String, Object> auth(String requestURL)  {

		requestURL = "https://{hostname}/api/1/printing/oauth2/auth/token?subject=printer";
		requestURL = requestURL.replace("{hostname}",APIConfig.hostname);
		
		///System.out.println(APIConfig.hostname);
		///System.out.println("requestURL : " + requestURL);
		///System.out.println("CLIENT_ID : " + APIConfig.clientId);
		///System.out.println("SECRET : " + APIConfig.secret);
		///System.out.println("DEVICE : " + APIConfig.device);
		
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
           
            //String	HOSTNAME = APIConfig.hostname; //"api.epsonconnect.com";
            //String ACCEPT = "application/json; charset=utf-8";
            //String CLIENT_ID = (String)APIConfig.clientId;//"1af87bb5aad845d2bd425d50aae44c8a";
            //String SECRET = "qXx4bVcjm65r6qSb5sr6SUj3VgmUNagZVGZwtHn2ta7rdZZaxsg7CJRz5riCYT1I";
            //String DEVICE = "qut6038ye0ni75@print.epsonconnect.com";  //프린터이메일 주소
            
            //String uri_str = 
            //webApiUri=https://api.epsonconnect.com/api/1/printing/ {%resource%} 
           
            //String teststr= CLIENT_ID+":"+SECRET;
            //byte[] testBytes = teststr.getBytes();
            
            //String encodedStr = Base64.getEncoder().encodeToString(teststr.getBytes());
            //System.out.println("encoded string: " + encodedStr);
            
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
            //String decodedStr = new String(decodedBytes);
            //System.out.println("decoded string: " + decodedStr);
            //String auth = encodedStr;
            //String auth = decodedStr;
            //String grant_type = "password";
            ///String usernamme = DEVICE;
            //String password ="";

            headers.setBasicAuth((String)APIConfig.clientId, (String)APIConfig.secret);
            headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            
            //MultiValueMap<String,String> body = new LinkedMultiValueMap<String,String>();
            //body.add("grant_type","password");
            //body.add("usernamme",DEVICE);
            //body.add("password","");
            
            
            //
         // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL)
                    .queryParam("subject", "printer")
                    .queryParam("grant_type", "password")
                    .queryParam("username", APIConfig.device)
                    .queryParam("password", "");
            
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            //System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);

            
            //System.out.println("resultMap string: " + resultMap);
            //System.out.println(resultMap);
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            
            System.out.println("!!!!!!!!!!!!!name : " + name);	
            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}
	


public HashMap<String, Object> refresh(HttpSession session , String requestURL)  {

		requestURL = "https://{hostname}/api/1/printing/oauth2/auth/token?subject=printer";
		requestURL = requestURL.replace("{hostname}",APIConfig.hostname);
		
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            
            //String CLIENT_ID = "1af87bb5aad845d2bd425d50aae44c8a";
           // String SECRET = "qXx4bVcjm65r6qSb5sr6SUj3VgmUNagZVGZwtHn2ta7rdZZaxsg7CJRz5riCYT1I";
            //String DEVICE ="qut6038ye0ni75@print.epsonconnect.com";  //프린터이메일 주소
            
            //String uri_str = 
            //webApiUri=https://api.epsonconnect.com/api/1/printing/ {%resource%} 
           
            //String teststr= CLIENT_ID+":"+SECRET;
            //byte[] testBytes = teststr.getBytes();
            
            //String encodedStr = Base64.getEncoder().encodeToString(teststr.getBytes());
            //System.out.println("encoded string: " + encodedStr);
            
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
            //String decodedStr = new String(decodedBytes);
            //System.out.println("decoded string: " + decodedStr);
            //String auth = encodedStr;
            //String auth = decodedStr;
            //String grant_type = "refresh_token";
            //String refresh_token = "NTEkTiRDnuPzPL7KcNMyZx7iu2s0tnmPFIY6oCs3IYK48SXj23PohYoEO1ViQzQB";
            //String usernamme = DEVICE;
            //String password ="";

            

            headers.setBasicAuth(APIConfig.clientId,APIConfig.secret);
            //headers.set("Authorization", "Basic "+auth);
            //headers.set("Authorization", "Basic "+teststr);
            //headers.set("Authorization", auth);
            headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //headers.set("Content-Type", "application/json; charset=utf-8");
            //headers.set("Accept", ACCEPT);
            //
            // MultiValueMap<String,String> body = new LinkedMultiValueMap<String,String>();

            //body.add("grant_type",grant_type);
            //body.add("usernamme",usernamme);
            //body.add("password",password);
            //System.out.println("body string: " + body);
            
            
            System.out.println("APIConfig.refreshToken : "+ (String)session.getAttribute("refreshToken"));
            //
            // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL)
                    .queryParam("subject", "printer")
                    //.queryParam("grant_type", "password")
                    .queryParam("grant_type", "refresh_token")
                    .queryParam("refresh_token", (String)session.getAttribute("refreshToken"));
                    //.queryParam("username", usernamme)
                    //.queryParam("password", "");
            
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            //System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);

            
            System.out.println("resultMap string: " + resultMap);
            System.out.println(resultMap);
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}




public HashMap<String, Object> capability(HttpSession session ,String requestURL)  {

		//requestURL = "https://{hostname}/api/1/printing/printers/{deviceId}/capability/document?";
		requestURL = "https://{hostname}/api/1/printing/printers/{deviceId}/capability/photo?";
		requestURL = requestURL.replace("{hostname}",APIConfig.hostname);
		requestURL = requestURL.replace("{deviceId}",(String)session.getAttribute("deviceId"));
		
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();
		//ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            
            //
            //MultiValueMap<String,String> body = new LinkedMultiValueMap<String,String>();
            //String	hostname = "api.epsonconnect.com";
            //String ACCEPT = "application/json; charset=utf-8";
            //String CLIENT_ID = "1af87bb5aad845d2bd425d50aae44c8a";
            //String SECRET = "qXx4bVcjm65r6qSb5sr6SUj3VgmUNagZVGZwtHn2ta7rdZZaxsg7CJRz5riCYT1I";
            //String DEVICE ="qut6038ye0ni75@print.epsonconnect.com";  //프린터이메일 주소
            
            //String uri_str = 
            //webApiUri=https://api.epsonconnect.com/api/1/printing/ {%resource%} 
           
            //String teststr= CLIENT_ID+":"+SECRET;
            //byte[] testBytes = teststr.getBytes();
            
            //String encodedStr = Base64.getEncoder().encodeToString(teststr.getBytes());
            //System.out.println("encoded string: " + encodedStr);
            
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
            //String decodedStr = new String(decodedBytes);
            //System.out.println("decoded string: " + decodedStr);
            //String auth = encodedStr;
            //String auth = decodedStr;
           // String grant_type = "refresh_token";
            //String refresh_token = "NTEkTiRDnuPzPL7KcNMyZx7iu2s0tnmPFIY6oCs3IYK48SXj23PohYoEO1ViQzQB";
           // String usernamme = DEVICE;
            //String password ="";
            //String access_token = "GIXyt1yi4h8YPz6SV5SdDPfAySVwVU1QYkezGHEhNvbGn9XLdD8nExWKeA4h4Eqr";

            headers.setBearerAuth((String)session.getAttribute("accessToken"));
            //headers.setBasicAuth(CLIENT_ID, SECRET);
            //headers.set("Authorization", "Basic "+auth);
            //headers.set("Authorization", "Basic "+teststr);
            //headers.set("Authorization", auth);
            //headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //headers.set("Content-Type", "application/json; charset=utf-8");
            //headers.set("Accept", ACCEPT);
            //
            
            //body.add("grant_type",grant_type);
            //body.add("usernamme",usernamme);
            //body.add("password",password);
            //System.out.println("body string: " + body);
            
            
            
            //
         // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL);
                    //.queryParam("subject", "printer")
                    //.queryParam("grant_type", "password")
                    //.queryParam("grant_type", grant_type)
                    //.queryParam("refresh_token", refresh_token);
                    //.queryParam("username", usernamme)
                    //.queryParam("password", "");
            
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            //System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            
            System.out.println("resultMap string: " + resultMap);
            System.out.println(resultMap);
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}


public HashMap<String, Object> setting(HttpSession session ,String requestURL)  {

		requestURL = "https://{hostname}/api/1/printing/printers/{deviceId}/jobs?";
		requestURL = requestURL.replace("{hostname}",APIConfig.hostname);
		requestURL = requestURL.replace("{deviceId}",(String)session.getAttribute("deviceId"));
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();
		//ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            
            //
            MultiValueMap<String,Object> body = new LinkedMultiValueMap<String,Object>();
            //String	hostname = "api.epsonconnect.com";
            //String ACCEPT = "application/json; charset=utf-8";
            //String CLIENT_ID = "1af87bb5aad845d2bd425d50aae44c8a";
            //String SECRET = "qXx4bVcjm65r6qSb5sr6SUj3VgmUNagZVGZwtHn2ta7rdZZaxsg7CJRz5riCYT1I";
            //String DEVICE ="qut6038ye0ni75@print.epsonconnect.com";  //프린터이메일 주소
            
            //String uri_str = 
            //webApiUri=https://api.epsonconnect.com/api/1/printing/ {%resource%} 
           
            //String teststr= CLIENT_ID+":"+SECRET;
            //byte[] testBytes = teststr.getBytes();
            
            //String encodedStr = Base64.getEncoder().encodeToString(teststr.getBytes());
            //System.out.println("encoded string: " + encodedStr);
            
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
            //String decodedStr = new String(decodedBytes);
            //System.out.println("decoded string: " + decodedStr);
            //String auth = encodedStr;
            //String auth = decodedStr;
            //tring grant_type = "refresh_token";
            //String refresh_token = "NTEkTiRDnuPzPL7KcNMyZx7iu2s0tnmPFIY6oCs3IYK48SXj23PohYoEO1ViQzQB";
            //String usernamme = DEVICE;
            //String password ="";
            //String access_token = "GIXyt1yi4h8YPz6SV5SdDPfAySVwVU1QYkezGHEhNvbGn9XLdD8nExWKeA4h4Eqr";

            headers.setBearerAuth((String)session.getAttribute("accessToken"));
            //headers.setBasicAuth(CLIENT_ID, SECRET);
            //headers.set("Authorization", "Basic "+auth);
            //headers.set("Authorization", "Basic "+teststr);
            //headers.set("Authorization", auth);
            //headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            headers.set("Content-Type", "application/json; charset=UTF-8");
            //headers.set("Accept", ACCEPT);
            //
            

            //JSONObject print_setting = new JSONObject();
            //print_setting.put("media_size","ms_a4");
            //print_setting.put("media_type","mt_plainpaper");
            //print_setting.put("borderless","false");
            //print_setting.put("print_quality","normal");
            //print_setting.put("source","front2");
            //print_setting.put("color_mode","mono");
            //print_setting.put("2_sided","none");
            //print_setting.put("reverse_order","false");
            //print_setting.put("copies","1");
            //print_setting.put("collate","true");
            
            
            //Map aa = new HashMap();
            //aa.put("media_size","ms_a4");
            //aa.put("media_type","mt_plainpaper");
            //aa.put("borderless",false);
            //aa.put("print_quality","normal");
           // aa.put("source","front2");
            //aa.put("color_mode","mono");
            //aa.put("2_sided","none");
            //aa.put("reverse_order",false);
            //aa.put("copies",1);
            //aa.put("collate",true);
            
            
            //body.add("grant_type",grant_type);
            //body.add("usernamme",usernamme);
            //body.add("password",password);
            //body.add("job_name","sample");
            //body.add("print_mode","document");
            //body.add("print_setting",print_setting);
            //body.add("print_setting",aa);
            
            //System.out.println("body string: " + body);
            ////////////////////////////////////////////////////////
            
            JSONObject parameter1 = new JSONObject();
            parameter1.put("media_size","ms_a4");
            parameter1.put("media_type","mt_plainpaper");
            parameter1.put("borderless",false);
            parameter1.put("print_quality","normal");
            parameter1.put("source","front2");
            parameter1.put("color_mode","mono");
            
            //parameter1.put("2_sided","none");
            //parameter1.put("reverse_order",false);
            //parameter1.put("copies",1);
            //parameter1.put("collate",true);
            
            // JSON 객체 선언 
         	JSONObject parameter = new JSONObject();
         	parameter.put("job_name","sample");
         	//parameter.put("print_mode","document");
         	parameter.put("print_mode","photo");
         	//parameter.put("print_setting",parameter1);
            
            
            //
           // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL);
                    //.queryParam("subject", "printer")
                    //.queryParam("grant_type", "password")
                    //.queryParam("grant_type", grant_type)
                    //.queryParam("refresh_token", refresh_token);
                    //.queryParam("username", usernamme)
                    //.queryParam("password", "");
            
            
            //HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            HttpEntity<?> entity = new HttpEntity<>(parameter.toJSONString(),headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            //System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);

            
            System.out.println("resultMap string: " + resultMap);
            System.out.println(resultMap);
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}




public HashMap<String, Object> upload(HttpSession session ,String param,String requestURL)  {

		//requestURL = "{upload_uri}/File=1.{extension}";
		//requestURL = "https://www.epsonconnect.com/c33fe124ef80c3b13670be27a6b0bcd7/v1/storage/PostData?Key=7ba2f33e17536d943896fff35b3e16c88e55f79d64356e339bdbab4f5ae680e8b9aa339c473be300/File=1.txt";
		//requestURL = "{uploadUri}";
		//requestURL = "{uploadUri}/File=1.{extension}";
		requestURL = "{uploadUri}&File=1.{extension}";
		//requestURL = "https://www.epsonconnect.com/c33fe124ef80c3b13670be27a6b0bcd7/v1/storage/PostData?";
		
		requestURL = requestURL.replace("{hostname}",APIConfig.hostname);
		requestURL = requestURL.replace("{uploadUri}",(String)session.getAttribute("uploadUri"));
		//requestURL = requestURL.replace("{extension}","txt");
		
		
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();
		//ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            
            //
            //MultiValueMap<String,String> body = new LinkedMultiValueMap<String,String>();
           // String	hostname = "api.epsonconnect.com";
           // String ACCEPT = "application/json; charset=utf-8";
           // String CLIENT_ID = "1af87bb5aad845d2bd425d50aae44c8a";
           // String SECRET = "qXx4bVcjm65r6qSb5sr6SUj3VgmUNagZVGZwtHn2ta7rdZZaxsg7CJRz5riCYT1I";
            //String DEVICE ="qut6038ye0ni75@print.epsonconnect.com";  //프린터이메일 주소
            
            //String uri_str = 
            //webApiUri=https://api.epsonconnect.com/api/1/printing/ {%resource%} 
           
           // String teststr= CLIENT_ID+":"+SECRET;
            //byte[] testBytes = teststr.getBytes();
            
            //String encodedStr = Base64.getEncoder().encodeToString(teststr.getBytes());
           // System.out.println("encoded string: " + encodedStr);
            
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
            //String decodedStr = new String(decodedBytes);
            //System.out.println("decoded string: " + decodedStr);
            //String auth = encodedStr;
           // String auth = decodedStr;
            //String grant_type = "refresh_token";
            //String refresh_token = "NTEkTiRDnuPzPL7KcNMyZx7iu2s0tnmPFIY6oCs3IYK48SXj23PohYoEO1ViQzQB";
            //String usernamme = DEVICE;
            //String password ="";

            //File file = new File("d:/epsonprint.txt");
            requestURL = requestURL.replace("{extension}","jpg");
            
            //param
            	File file;
            if(param.equals("problem")) {
            	//File file2 = new File(".");
                //String rootPath = file2.getAbsolutePath();
                String rootPath = System.getProperty("user.dir");
                String pass = rootPath+"/"+param+".jpg";
                //pass=pass.replaceAll("\\", "/");
                System.out.println("1현재 프로젝트의 경로 : "+ pass );
            	file = new File(pass);
            }else if(param.equals("bom")){
            	String rootPath = System.getProperty("user.dir");
                String pass = rootPath+"/"+param+".jpg";
                System.out.println("2현재 프로젝트의 경로 : "+pass );
            	file = new File(pass);
            }else {
            	String rootPath = System.getProperty("user.dir");
                String pass = rootPath+"/"+param+".jpg";
                System.out.println("2현재 프로젝트의 경로 : "+pass );
            	file = new File(pass);
            	//file = new File("d:\\bom.jpg");
            }
            
            
            // 파일 존재 여부 확인
            if (file.exists()) {
                // 파일이 존재할 때만 처리
                System.out.println("파일이 존재합니다: " + file.getAbsolutePath());
            } else {
                System.out.println("파일이 존재하지 않습니다: " + file.getAbsolutePath());
                // 예외 처리 또는 대체 로직
            }
            
            
            FileInputStream fis= new FileInputStream(file);
            byte[] byteBuff = new byte[9999];
            System.out.println("!byteBuff[0]: " + byteBuff[0]);
			System.out.println("!byteBuff[1]: " + byteBuff[1]);
			System.out.println("!byteBuff[2]: " + byteBuff[2]);
			System.out.println("!byteBuff[3]: " + byteBuff[3]);
			System.out.println("!byteBuff[4]: " + byteBuff[4]);

			// 파일을 읽고 읽은 크기를 nRLen 에 저장한다.
			int nRLen = fis.read(byteBuff);
			System.out.println("nRLen : " + nRLen);
			System.out.println("byteBuff[0]: " + byteBuff[0]);
			System.out.println("byteBuff[1]: " + byteBuff[1]);
			System.out.println("byteBuff[2]: " + byteBuff[2]);
			System.out.println("byteBuff[3]: " + byteBuff[3]);
			System.out.println("byteBuff[4]: " + byteBuff[4]);
			
            
            //BufferedReader reader = new BufferedReader(    new InputStreamReader(new FileInputStream("d:\\epsonprint.txt"), "UTF-8"));
            
            //byte[] bytes = Files.readAllBytes(Paths.get("d:\\epsonprint.txt"));
            //String str = Files.readString(Paths.get("d:\\epsonprint.txt"));
            
            //String str2 = Files.readString(Paths.get("d:\\epsonprint.docx"));
            //requestURL = requestURL.replace("{extension}","docx");
            //System.out.println("str : " + str);
            
            //bytes = str.getBytes();
            
            String main = "혹시 이거 프린터 되시나요?? \r\n"
            		+ "\r\n"
            		+ "확인 되시면 카톡 부탁드립니다.\r\n"
            		+ "\r\n"
            		+ "from 남천우.";
            //System.out.println("length : "+String.valueOf(str.getBytes().length));
            
            //headers.set("Content-Length", String.valueOf(str.getBytes().length) );
            headers.set("Content-Length", String.valueOf(nRLen) );
            //headers.set("Content-Type", "application/octet-stream");
            headers.set("Content-Type", "image/jpeg");
            //headers.set("Content-Type", "application/pdf; charset=utf-8");
            
            //headers.setBasicAuth(CLIENT_ID, SECRET);
            //headers.set("Authorization", "Basic "+auth);
            //headers.set("Authorization", "Basic "+teststr);
            //headers.set("Authorization", auth);
            //headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //headers.set("Content-Type", "application/json; charset=utf-8");
            //headers.set("Accept", ACCEPT);
            //
            
            //body.add("grant_type",grant_type);
            //body.add("usernamme",usernamme);
            //body.add("password",password);
            //body.add();
            //System.out.println("body string: " + body);
            
           
            
            //
         // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL);
            		//.queryParam("Key", "883fe77fc6a8186138872ead119c34bad1ee232f697157651a5a476120e04d967640097e5603a388")
            		//.queryParam("File", "1.txt");
            		//.queryParam("subject", "printer");
            		//.queryParam("subject", "printer")
                    //.queryParam("grant_type", "password")
                    //.queryParam("grant_type", grant_type)
                    //.queryParam("refresh_token", refresh_token);
                    //.queryParam("username", usernamme)
                    //.queryParam("password", "");
            
            
            //HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            //HttpEntity<?> entity = new HttpEntity<>(main,headers);
            //HttpEntity<?> entity = new HttpEntity<>(bytes,headers);
            HttpEntity<?> entity = new HttpEntity<>(byteBuff,headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);

            
            System.out.println("resultMap string: " + resultMap);
            //System.out.println(resultMap);
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}


public HashMap<String, Object> execute(HttpSession session ,String requestURL)  {

	requestURL = "https://{hostname}/api/1/printing/printers/{deviceId}/jobs/{jobId}/print";
	// https://xxx.xxx.xxx/api/1/printing/printers/{device_id}/jobs/{job_id}/print

	requestURL = requestURL.replace("{hostname}",APIConfig.hostname);
	requestURL = requestURL.replace("{deviceId}",(String)session.getAttribute("deviceId"));
	requestURL = requestURL.replace("{jobId}",(String)session.getAttribute("settingId"));
	
	//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();
		//ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            
            //
            MultiValueMap<String,String> body = new LinkedMultiValueMap<String,String>();
            //String	hostname = "api.epsonconnect.com";
            //String ACCEPT = "application/json; charset=utf-8";
            //String CLIENT_ID = "1af87bb5aad845d2bd425d50aae44c8a";
            //String SECRET = "qXx4bVcjm65r6qSb5sr6SUj3VgmUNagZVGZwtHn2ta7rdZZaxsg7CJRz5riCYT1I";
            //String DEVICE ="qut6038ye0ni75@print.epsonconnect.com";  //프린터이메일 주소
            
            //String uri_str = 
            //webApiUri=https://api.epsonconnect.com/api/1/printing/ {%resource%} 
           
            //String teststr= CLIENT_ID+":"+SECRET;
            //byte[] testBytes = teststr.getBytes();
            
            //String encodedStr = Base64.getEncoder().encodeToString(teststr.getBytes());
            //System.out.println("encoded string: " + encodedStr);
            
            //byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
            //String decodedStr = new String(decodedBytes);
            //System.out.println("decoded string: " + decodedStr);
            //String auth = encodedStr;
            //String auth = decodedStr;
           // String grant_type = "refresh_token";
            //String refresh_token = "NTEkTiRDnuPzPL7KcNMyZx7iu2s0tnmPFIY6oCs3IYK48SXj23PohYoEO1ViQzQB";
            //String usernamme = DEVICE;
            //String password ="";

            
            //String access_token = "GIXyt1yi4h8YPz6SV5SdDPfAySVwVU1QYkezGHEhNvbGn9XLdD8nExWKeA4h4Eqr";

            headers.setBearerAuth((String)session.getAttribute("accessToken"));
            //headers.set("Authorization", "Basic "+auth);
            //headers.set("Authorization", "Basic "+teststr);
            //headers.set("Authorization", auth);
            //headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            headers.set("Content-Type", "application/json; charset=utf-8");
            //headers.set("Content-Type", "application/plain; charset=utf-8");
            //headers.set("Accept", ACCEPT);
            //
            
            //body.add("grant_type",grant_type);
            //body.add("usernamme",usernamme);
            //body.add("password",password);
            //System.out.println("body string: " + body);
            
            
            
            //
         // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL);
                    //.queryParam("subject", "printer")
                    //.queryParam("grant_type", "password")
                    //.queryParam("grant_type", grant_type)
                    //.queryParam("refresh_token", refresh_token);
                    //.queryParam("username", usernamme)
                    //.queryParam("password", "");
            
            
            HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);

            
            System.out.println("resultMap string: " + resultMap);
            System.out.println(resultMap);
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}



public HashMap<String, Object> ocr(HttpSession session, String imgurl,String requestURL)  {
		
		//Spring restTemplate
        HashMap<String, Object> result = new HashMap<String, Object>();
		//ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            
            MultiValueMap<String,Object> body = new LinkedMultiValueMap<String,Object>();
          
            //headers.setBearerAuth(access_token);
            //headers.setBasicAuth(CLIENT_ID, SECRET);
            //headers.set("Authorization", "Basic "+auth);
            //headers.set("Authorization", "Basic "+teststr);
            //headers.set("Authorization", auth);
            //headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            //headers.set("Content-Type", "application/json; charset=UTF-8");
            headers.set("Content-Type", "application/json");
            headers.set("X-OCR-SECRET", APIConfig.XOCRSECRET );
            
            //headers.set("Accept", ACCEPT);
            //
            JSONObject print_setting = new JSONObject();
            print_setting.put("media_size","ms_a4");
            print_setting.put("media_type","mt_plainpaper");
            print_setting.put("borderless","false");
            print_setting.put("print_quality","normal");
            print_setting.put("source","front2");
            print_setting.put("color_mode","mono");
            print_setting.put("2_sided","none");
            print_setting.put("reverse_order","false");
            print_setting.put("copies","1");
            print_setting.put("collate","true");
            
            
            Map aa = new HashMap();
            aa.put("media_size","ms_a4");
            aa.put("media_type","mt_plainpaper");
            aa.put("borderless",false);
            aa.put("print_quality","normal");
            aa.put("source","front2");
            aa.put("color_mode","mono");
            aa.put("2_sided","none");
            aa.put("reverse_order",false);
            aa.put("copies",1);
            aa.put("collate",true);
            
            
            //body.add("grant_type",grant_type);
            //body.add("usernamme",usernamme);
            //body.add("password",password);
            body.add("job_name","sample");
            body.add("print_mode","document");
            body.add("print_setting",print_setting);
            //body.add("print_setting",aa);
            
            System.out.println("body string: " + body);
            
            
            JSONObject parameter1 = new JSONObject();
            parameter1.put("format", "png");
            parameter1.put("name", "medium");
            parameter1.put("data", null);
            parameter1.put("url", imgurl);
            JSONArray array = new JSONArray();
            array.add(parameter1);
            
            // JSON 객체 선언 
         	JSONObject parameter = new JSONObject();
         	parameter.put("images", array);
         	parameter.put("lang", "ko");
         	parameter.put("requestId", "string");
         	parameter.put("resultType", "string");
         	//parameter.put("timestamp","{{$timestamp}}");
         	parameter.put("timestamp","1719126690518");
         	parameter.put("version","V1");
         	
         	
            
            //
         // 쿼리 파라미터 구성
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(requestURL);
                    //.queryParam("subject", "printer")
                    //.queryParam("grant_type", "password")
                    //.queryParam("grant_type", grant_type)
                    //.queryParam("refresh_token", refresh_token);
                    //.queryParam("username", usernamme)
                    //.queryParam("password", "");
            
            
            
            
            
            //HttpEntity<?> entity = new HttpEntity<>(headers);
            //HttpEntity<?> entity = new HttpEntity<>(body,headers);
            HttpEntity<?> entity = new HttpEntity<>(parameter.toJSONString(),headers);
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(requestURL).build();
            UriComponents uri = builder.build();
            //System.out.println("uri string: " + uri);
            ResponseEntity<?> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.POST, entity, Object.class);

            
            System.out.println("resultMap string: " + resultMap);
            System.out.println(resultMap);
            
            
            
            
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인
            
            //result.put("all", resultMap); //실제 데이터 정보 확인
            

            //에러처리해야댐
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("error");
            System.out.println(e.toString());

			//return resultMap;
        }
        catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println("999");
            System.out.println(e.toString());
 
 			//return resultMap;

        }

        return result;
	}


}
