
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
	
	
	
	$('#header').on('click', function(){
		console.log('daul.!');
	});
		
	
});

