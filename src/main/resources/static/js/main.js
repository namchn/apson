
$(document).ready(function() {
	$('.test-btn').on('click', function(){
	   alert("ddd");
	   
	   $.get("/print/auth", function(data, status){
		   
		var myJSON = JSON.stringify(data);   
		//var obj = JSON.parse(data); 
		   
	    console.log("Data: " + myJSON + "\nStatus: " + status);
	    //console.log("Data: " + obj + "\nStatus: " + status);
	    
	    
	    //JSON 데이터를 자바스크립트 객체로 변환
	    var obj = JSON.parse(myJSON); 
	    console.log("Data: " + obj + "\nStatus: " + status);
	    
	    for(var i in obj) {
            console.log(obj[i]);
        }
	    
	    
	  });
	   
	   
	   //$.get('ajax/test.html', function(data) {
		   
		   
		  //$('.result').html(data);
		  //alert('Load was performed.');
		//});	
				   
	});
	
	
	///
	$('#loginAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		alert('로그인 되었습니다. 그리고 프린트 권한을 획득하였습니다.');
		$.get("/print/auth", function(data, status){
		
		var myJSON = JSON.stringify(data);   
		//var obj = JSON.parse(data); 
		   
	    console.log("Data: " + myJSON + "\nStatus: " + status);
	    //console.log("Data: " + obj + "\nStatus: " + status);
	    
	    
	    //JSON 데이터를 자바스크립트 객체로 변환
	    var obj = JSON.parse(myJSON); 
	    console.log("Data: " + obj + "\nStatus: " + status);
	    
	    for(var i in obj) {
            console.log(obj[i]);
        }
	    
	    
	  });
	});
	///
	$('#printAction').on('click', function(){
		console.log('프린트 영역.');
		// refresh
		// setting
		// upload
		// excute 
		//alert('프린트하였습니다.');
	});
	$('#print-setting-img').on('click', function(){
		// console.log('로그인 되었습니다.');
		// refresh
		// setting
		 $.get("/print/setting", function(data, status){
		   
			var myJSON = JSON.stringify(data);   
			//var obj = JSON.parse(data); 
			   
		    console.log("Data: " + myJSON + "\nStatus: " + status);
		    //console.log("Data: " + obj + "\nStatus: " + status);
		    
		    
		    //JSON 데이터를 자바스크립트 객체로 변환
		    var obj = JSON.parse(myJSON); 
		    console.log("Data: " + obj + "\nStatus: " + status);
		    
		    for(var i in obj) {
	            console.log(obj[i]);
	        }
		    
		    alert('이미지파일로 원격프린트 세팅되었습니다.');
		  });
		// upload
		// excute 
		//alert('프린트하였습니다.');
	});
	$('#print-setting-txt').on('click', function(){
		// console.log('로그인 되었습니다.');
		// refresh
		// setting
		 $.get("/print/setting", function(data, status){
		   
			var myJSON = JSON.stringify(data);   
			//var obj = JSON.parse(data); 
			   
		    console.log("Data: " + myJSON + "\nStatus: " + status);
		    //console.log("Data: " + obj + "\nStatus: " + status);
		    
		    
		    //JSON 데이터를 자바스크립트 객체로 변환
		    var obj = JSON.parse(myJSON); 
		    console.log("Data: " + obj + "\nStatus: " + status);
		    
		    for(var i in obj) {
	            console.log(obj[i]);
	        }
		    
		    alert('텍스트파일로 원격프린트 세팅되었습니다.');
		  });
		// upload
		// excute 
		//alert('프린트하였습니다.');
	});
	$('#print-upload-problem').on('click', function(){
		// console.log('로그인 되었습니다.');
		// refresh
		// setting
		 $.get("/print/upload?param=problem", function(data, status){
		   
			var myJSON = JSON.stringify(data);   
			//var obj = JSON.parse(data); 
			   
		    console.log("Data: " + myJSON + "\nStatus: " + status);
		    //console.log("Data: " + obj + "\nStatus: " + status);
		    
		    
		    //JSON 데이터를 자바스크립트 객체로 변환
		    var obj = JSON.parse(myJSON); 
		    console.log("Data: " + obj + "\nStatus: " + status);
		    
		    for(var i in obj) {
	            console.log(obj[i]);
	        }
		    
		    alert('원격프린트로 프린트정보 전송되었습니다.');
		  });
		// upload
		// excute 
		//alert('프린트하였습니다.');
	});
	$('#print-execute').on('click', function(){
		// console.log('로그인 되었습니다.');
		// refresh
		// setting
		 $.get("/print/execute", function(data, status){
		   
			var myJSON = JSON.stringify(data);   
			//var obj = JSON.parse(data); 
			   
		    console.log("Data: " + myJSON + "\nStatus: " + status);
		    //console.log("Data: " + obj + "\nStatus: " + status);
		    
		    
		    //JSON 데이터를 자바스크립트 객체로 변환
		    var obj = JSON.parse(myJSON); 
		    console.log("Data: " + obj + "\nStatus: " + status);
		    
		    for(var i in obj) {
	            console.log(obj[i]);
	        }
		    
		    alert('원격프린트로 인쇄를 시작합니다.');
		  });
		// upload
		// excute 
		//alert('프린트하였습니다.');
	});
	
	
	//$('#printAction').on('click', function(){
		// console.log('로그인 되었습니다.');
		// refresh
		// setting
		// upload
		// excute 
		//alert('프린트하였습니다.');
	//});
	
	
	///
	//$('#scanAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		//refresh
		//setting
		//scan
		//alert('스캔되었습니다.');
	//});
	///
	//$('#solutionAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		//llm 언어 모델 
		
		//alert('문제가 분석되었습니다.');
	//});
	$('#print-analy').on('click', function(){
		// console.log('로그인 되었습니다.');
		// refresh
		// setting
		 $.get("/print/ocr", function(data, status){
		   
			var myJSON = JSON.stringify(data);   
			//var obj = JSON.parse(data); 
			   
		    console.log("Data: " + myJSON + "\nStatus: " + status);
		    //console.log("Data: " + obj + "\nStatus: " + status);
		    
		    console.log(myJSON['data'] );
		    console.log(myJSON.data );
		    console.log("Data============================= ");
		    console.log(myJSON.data );
		    console.log(myJSON[0] );
		    //JSON 데이터를 자바스크립트 객체로 변환
		    var obj = JSON.parse(myJSON); 
		    console.log("Data: " + obj + "\nStatus: " + status);
		    console.log(obj['data']);
		    console.log(obj[0]);
		    console.log(obj.data);
		    
		    $('#anal-data').html(myJSON);
		    var obj2 = obj.data[0];
		    //console.log(obj2.fields);
		    var obj3 = obj2.fields;
		    
		    var memo ="";
		    for(var i in obj3) {
	           console.log(obj3[i].inferText);
	           memo +=obj3[i].inferText +", "
	        }
		    
		    $('#anal-data').html(memo);
		    //$('#anal-data').html(myJSON);
		    
		    
		    alert('문제를 분석합니다.');
		  });
		// upload
		// excute 
		//alert('프린트하였습니다.');
	});	
	
});

