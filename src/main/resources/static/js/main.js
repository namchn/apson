
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
	
	
	
	$('#loginAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		alert('로그인 되었습니다.');
	});
	$('#printAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		alert('프린트하였습니다.');
	});
	$('#scanAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		alert('스캔되었습니다.');
	});
	$('#solutionAction').on('click', function(){
		//console.log('로그인 되었습니다.');
		alert('문제가 분석되었습니다.');
	});
		
	
});

