package com.epson.enovation.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epson.enovation.api.service.ApiService;
import com.epson.enovation.dto.ResponseDTO;
import com.epson.enovation.model.APIConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("print")
public class PrintController {

	@Autowired
	private ApiService apiService;
	
	@GetMapping({"/",""})
	public String payCheck() {
		log.info("api..");
		return "The print service  is up and running...";
	}
	
	//업비트 코인 자동매매 RSI 값 구하기 (Java)
			//https://herojoon-dev.tistory.com/156
			
			// https://docs.upbit.com/reference/%EB%B6%84minute-%EC%BA%94%EB%93%A4-1
			//HashMap<String, Object> result = apiService.get("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=1");
			//HashMap<String, Object> result = apiService.get("https://api.upbit.com/v1/market/all?isDetails=false");
	
	@GetMapping("/auth")
	public ResponseEntity<?> auth(HttpServletRequest request)  {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.auth("");
		//
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		

        //2. Parser
        //JSONParser jsonParser = new JSONParser();
        //3. To Object
        //Object obj = jsonParser.parse((String) result.get("body"));
        //4. To JsonObject
        //JSONObject jsonObj = (JSONObject) obj;
        
        
        Map map =  (Map) result.get("body");
		session.setAttribute("refreshToken", (String) map.get("refresh_token"));
		session.setAttribute("accessToken", (String) map.get("access_token"));
		session.setAttribute("deviceId", (String) map.get("subject_id"));
        
        //APIConfig.refreshToken = (String) map.get("refresh_token");
        //APIConfig.accessToken = (String) map.get("access_token");
        //APIConfig.deviceId = (String) map.get("subject_id");
        //System.out.print(APIConfig.deviceId);
		
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	
	
	@GetMapping("/refresh")
	public ResponseEntity<?> refresh(HttpServletRequest request) {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.refresh(session,"");
		//
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		Map map =  (Map) result.get("body");
		session.setAttribute("accessToken", (String) map.get("access_token"));
		session.setAttribute("deviceId", (String) map.get("subject_id"));
        
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	
	
	@GetMapping("/capability")
	public ResponseEntity<?> capability(HttpServletRequest request) {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.capability(session,"");
		//
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	
	
	@GetMapping("/setting")
	public ResponseEntity<?> setting(HttpServletRequest request) {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.setting(session,"");
		
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		
		Map map =  (Map) result.get("body");
		session.setAttribute("settingId", (String) map.get("id"));
		session.setAttribute("uploadUri", (String) map.get("upload_uri"));
        
		
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	
	@GetMapping("/upload")
	public ResponseEntity<?> upload(HttpServletRequest request) {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.upload(session,"");
		//
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	
	@GetMapping("/execute")
	public ResponseEntity<?> execute(HttpServletRequest request) {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.execute(session,"");
		//
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	
	
	@GetMapping("/ocr")
	public ResponseEntity<?> ocr() {

		HashMap<String, Object> result = apiService.ocr("");
		//
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		
		
		List<Object> list = new ArrayList<Object>();
		list.add(result.get("body"));
		list.add(result.get("statusCode"));
		list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	

	
}
