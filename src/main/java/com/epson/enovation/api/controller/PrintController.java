package com.epson.enovation.api.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.epson.enovation.api.service.ApiService;
import com.epson.enovation.dto.ResponseDTO;
import com.epson.enovation.model.APIConfig;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
	
	
	@PostMapping("/upfile")
	public void upfile(HttpServletRequest request,
						HttpServletResponse response,
			 						@RequestPart("file") MultipartFile file
									//,@RequestParam(required = false,name="param", defaultValue="test") String param
									) throws IllegalStateException, IOException {

		HttpSession session = request.getSession();
		
		
		File file2 = new File(".");
        //String rootPath = file2.getAbsolutePath();
        String rootPath = System.getProperty("user.dir");;
        System.out.println("현재 프로젝트의 경로 : "+rootPath );

		
		//System.getProperty("user.dir") : 현재 프로젝트 경로를 가져옴
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\webapp";

        UUID uuid = UUID.randomUUID();//랜덤이름생성
        //String fileName = uuid + "_" + file.getOriginalFilename();
        String fileName = file.getOriginalFilename();
        
        String name = fileName;
        name= name.replace(".jpg", "");
        
        session.setAttribute("upfilename", name);
        
        //파일을 담을 껍데기를 만들어 파일경로와 파일이름을 매개변수로 넣고
        File saveFile = new File(rootPath, fileName);
        //throws Exception으로 예외처리
        //업로드된 파일을 껍데기에 담아 저장해준다
        if (saveFile.exists()) {
            // 파일이 존재할 때만 처리
            System.out.println("파일이 존재합니다: " + saveFile.getAbsolutePath());
        } else {
        	file.transferTo(saveFile);
        }
        
        
        
        //board 객체에 파일이름과 파일경로를 set()하여 DB에 저장한다
        //board.setFilename(fileName);
        //board.setFilepath("/webapp/" + fileName);
        //boardRepository.save(board);
        
        List<Object> list = new ArrayList<Object>();
		list.add(saveFile.getAbsolutePath());
		
        //ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
        //return ResponseEntity.status(200).body(response);
        
        
        String redirect_uri="/hello3#contact";
        response.sendRedirect(redirect_uri);
	}
	
	
	@GetMapping("/upload")
	public ResponseEntity<?> upload(HttpServletRequest request,
			   					@RequestParam(required = false,name="param", defaultValue="test") String param
									) {

		HttpSession session = request.getSession();
		HashMap<String, Object> result = apiService.upload(session,param,"");
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
	public ResponseEntity<?> ocr(
			@RequestParam(required = false,name="param", defaultValue="test") String param
			,HttpServletRequest request) throws ParseException {

		HttpSession session = request.getSession();
		
		String host = APIConfig.myhost;
		String imgurl = host+"/attach/images/"+(String)session.getAttribute("upfilename")+".jpg";
		System.out.println("getRemoteAddr : "+ (String)request.getRemoteAddr());
		
		if("0:0:0:0:0:0:0:1".equals((String)request.getRemoteAddr())) {
			imgurl ="https://scontent-gmp1-1.xx.fbcdn.net/v/t1.6435-9/90560345_3009242059140280_746351048439889920_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=5f2048&_nc_ohc=HXgALtTC39gQ7kNvgFhJWeB&_nc_ht=scontent-gmp1-1.xx&oh=00_AYDLWGDVRvc_dd04fqzbNUde8HWAC4dEgHpmHZyozk568g&oe=669F4795";
			System.out.println("imgurl : "+ imgurl);
		}
		
		HashMap<String, Object> result = apiService.ocr(session,imgurl,APIConfig.ocrUrl);
		//HashMap<String, Object> result = apiService.test("https://api.upbit.com/v1/ticker?markets=KRW-BTC");
		

		
		//2. Parser
        //JSONParser jsonParser = new JSONParser();
        //3. To Object
        //Object obj = jsonParser.parse((String) result.get("body"));
        //4. To JsonObject
        //JSONObject jsonObj = (JSONObject) obj;
        //JSONArray  ja1= (JSONArray)jsonObj.get("images");
		
        
        HashMap<String, Object> map1 = (HashMap) result.get("body");
        ArrayList<Map>  al2 = (ArrayList<Map>)map1.get("images");
		System.out.println(al2.get(0));
		HashMap<String, Object> map3 = (HashMap) al2.get(0);
		JSONObject jsonObject = new JSONObject(map3);
		
		List<Object> list = new ArrayList<Object>();
		list.add(jsonObject);
		//list.add(result.get("body"));
		//list.add(result.get("statusCode"));
		//list.add(result.get("header"));
		//list.add(result.get("all"));
		
		ResponseDTO<Object> response = ResponseDTO.<Object>builder().error("").data(list).build();
		// http status 200를 원한다면 ResponseEntity.ok()
		// http status를 404로 설정  ResponseEntity.badRequest()
		return ResponseEntity.status(200).body(response);
	}
	

	
}
