<!DOCTYPE html>
<html lang="en">

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.javaguides.springboot.main.utils.ConfigConstants"%>
 <!-- Link to an external CSS file from a CDN -->
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

 <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    

<style>
	
	.contaner1{
	   width: 100%;
	   height: 100vh;
       background-color:#115368;
       background: linear-gradient(45deg, #115368 50%, transparent 50%),
         linear-gradient(-45deg, rgb(240 185 69) 50%, transparent 50%);
	}
	#contaner2{
       align-items: center;
       justify-content: center;
       display: flex;
       width: 100%;
       height: 93%;
       display: flex;
	   align-items: center;
	}
	.img1{
			width: 943px;
			height: 100%;
			border-radius: 5px;
	}
	.col1{
		width: 50%;
	    height: 100%;
		position: relative;
		left: -166px;
	}
	.col2{
		position: relative;
		left: 75px;
	    width: 50%;
	}
	
	#loginDiv{
		width: 600px;
		height: 400px;
        background-color:#fffdfd;
        border-radius: 17%;
        align-content: center;
	}
	#he1{
		display: flex;
		align-items: center;
		color: white;
	}
	.inp1{
		    border-radius: 23px;
		    width: 253px;
		    height: 44px;
	}
	
	.s1{
		display: flex;
	   align-items: center;
	}
	input::placeholder {
            color: #999; /* Set the color */
            font-style: italic; /* Set the font style */
            font-size: 18px; /* Set the font size */
     }
   input:focus {
        border-color:rgb(192, 192, 192);
        outline: none; 
        color: rgb(255, 255, 255);
        background-color: #115368;
    }
    button{
		    width: 72px;
		    border-radius: 11px;
		    left: -74px;
		    position: relative;
		    top: -33px;
	}
	 button:focus{
		border-color:rgb(192, 192, 192);
        outline: none; 
        color: rgb(255, 255, 255);
        background-color: #115368;
	}
	 .ap:focus{
		border-color:rgb(192, 192, 192);
        outline: none; 
        color: rgb(255, 255, 255);
        background-color: #115368;
	}
	.ap{
		    position: relative;
		    left: 158px;
		    top: -7px;
		    background-color: rgb(15 77 96);
		    width: 100px;
		    color: #e3dbcb;
		    font: 22px;
		    font-size: 17px;
		    font-family: initial;
	}
	body {
    overflow-x: hidden;
    }
    .img1{
          width: 1000px; 
          height: 100%;
          clip-path: polygon(0% 0%, 88% 0%, 88% 75%, 0% 75%);   
    }
</style>

<body>
    <div id="contaner1"  class="contaner1">
		<h1 id="he1">IITH (Tax Data Pulling): :</h1>
	  <div id="contaner2">	
		  <div class="col1" style="">
			  <img class="img1" src="https://www.shutterstock.com/image-vector/mahatma-gandhi-indian-freedom-fighter-600w-2356339795.jp" alt="Description of the image">
		  </div>	  


		  <div class="mainDiv">
			 <p style="position: relative;top: 38px;left: 277px;color:red" id="errorMessage"></p>

			<button class="ap" id="signup" style="position: relative;left: 158px;top: -7px;">SignUp</button>
		    <button class="ap" id="login"  style="position: relative;left: 158px;top: -7px;">Login</button>  
			  <div id="loginDiv" class="col2">
<!--				  <p style="position: relative;top: 14px;left: 130px;color:red" id="errorMessage"></p>
-->				  <div style="position: relative;top: 95px;left: 176px;">
					   <span class="s1">  <input type="text" class="inp1 prq" id="loginUsername" placeholder="User Name"/></span> 
				  </div>
				   <div style="position: relative;bottom: -132px;left: 177px;">
					   <span class="s1">  <input type="text" class="inp1 prq" id="loginPassword" placeholder="Password"/></span> 
				  </div>
				  <div style="position: relative;bottom: -202px;left: 336px;">
	                     <button id="loginBtn">Login</button>
				 </div>
			  </div>
		  </div>
	   </div>
     </div>
</body>
</html>
<script>
   var basePath = "<%= ConfigConstants.basePath()%>";

	  $(document).ready(function(){
		  $("#login").hide();
		  $("#signup").click(function(){
			 $("#loginDiv").remove();
			 $("#signup").hide();
			 $("#login").show();
			 $("#errorMessage").text("");

			 var html='';
			    html+='	<div id="loginDiv" class="col2">';
			//	html+=' <p style="position: relative;top: 14px;left: 130px;color:red" id="errorMessage"></p>';
			    html+= '<div style="position: relative;top: 95px;left: 176px;">';
				html+=   '<span class="s1" style=""><input type="text" class="inp1 prq" id="signUpName" placeholder="Name"/>  </span>';
			    html+= '</div>';
			    html+= '<div style="position: relative;bottom: -132px;left: 177px;">';
				html+=   '<span class="s1">  <input type="text" class="inp1 prq" id="signUpUserName" placeholder="User Name"/></span>';
			    html+= '</div>';
			    html+= '<div style="position: relative;bottom: -167px;left: 179px;">';
				html+= '  <span class="s1">  <input type="text" class="inp1 prq" id="signUpPassword" placeholder="Password"/></span>';
			    html+= ' </div>';
			    html+= ' <div style="position: relative;top: 228px;left: 340px;">';
                html+= '     <button id="signUpBut">SignUp</button>';
			    html+= '   </div>';
			    html+= '   </div>';
			    $(".mainDiv").append(html)
		  });
		  
		  
		  $("#login").click(function(){
			   $("#loginDiv").remove();
			   $("#signup").show();
			   $("#login").hide();
			   $("#errorMessage").text("");

			    var htm='';
			    htm+='<div id="loginDiv" class="col2">';
		//		htm+=  '<p style="position: relative;top: 14px;left: 130px;color:red" id="errorMessage"></p>';
				htm+= '<div style="position: relative;top: 95px;left: 176px;">';
			    htm+=	 '  <span class="s1">  <input type="text" class="inp1 prq" id="loginUsername" placeholder="User Name"/></span>';
				htm+=  '</div>';
				htm+=  '<div style="position: relative;bottom: -132px;left: 177px;">';
				htm+=	   '<span class="s1">  <input type="text" class="inp1 prq" id="loginPassword" placeholder="Password"/></span>';
				htm+= '</div>';
				htm+=  '<div style="position: relative;bottom: -202px;left: 336px;">';
	            htm+=         '<button id="loginBtn">Login</button>';
				htm+= '</div>';
			    htm+='</div>';
			    $(".mainDiv").append(htm)
		  });
		  
         $(".mainDiv").on("click", "#signUpBut", function() {
			  var validation=[];

			  var json={};
			  json.name      = $.trim($("#signUpName").val());
			  json.userName  = $.trim($("#signUpUserName").val());
			  json.userPassword=$.trim($("#signUpPassword").val());
			  
			  validation.push({"type":"string","size":"5","mandatory":true,"id":"signUpName","displayId":"Name","caption":""});
			  validation.push({"type":"string","size":"5","mandatory":true,"id":"signUpUserName","displayId":"User Name","caption":""});
			  validation.push({"type":"string","size":"7","mandatory":true,"id":"signUpPassword","displayId":"Password","caption":""});

			  
             if(validationData(validation)){
				   $.ajax({
	                    url: basePath+'/mainLogin/signUp',
	                    method: 'POST',
	                    data: {data: JSON.stringify(json)}, 
	                    dataType: 'json',
	                    success: signUpSuccess,
	                    error:  loginError
	                });
                }else{
				  return false;
			  }
		  });  
		  
		   $(".mainDiv").on("click", "#loginBtn", function() {
			  var validation=[];
              
			  var json={};
			  json.userName=$.trim($("#loginUsername").val());
			  json.userPassword=$.trim($("#loginPassword").val());
			  
			  validation.push({"type":"string","size":"5","mandatory":true,"id":"loginUsername","displayId":"UserName","caption":""});
			  validation.push({"type":"string","size":"7","mandatory":true,"id":"loginPassword","displayId":"Password","caption":""});

              if(validationData(validation)){
				   $.ajax({
		                url: basePath+'/mainLogin/login',
		                method: 'POST',
		                data: { data: JSON.stringify(json)}, 
		                dataType: 'json',
		                success: loginSuccess,
		                error:  loginError
		            });
			  }else{
				  return false;
			  }
              
		  });  
  });
 
	
	function loginSuccess(data){
	
	     if(data.status=='1004a'){
			 $("#errorMessage").text("User Name is Not Valid");
		 }else if(data.status=='1004b'){
			 $("#errorMessage").text("Password is Not Valid");
		 }else{
			 window.location.href = basePath+"/dashbord";
		 }
	}
	function loginError(data){
		 alert("Some Unkown Error Occurs")

	}
	
	
	function signUpSuccess(data){
		  if(data.status=='1004a'){
			 $("#errorMessage").text("User Name is Already Present");
		 }else{
			 window.location.href = basePath+"/loginDataUrl";
		 }
		
	}
	function signUpError(data){
		 alert("Some Unkown Error Occurs")
	}
	
	
	function validationData(valid){
		$(".prq").css({ 'border': '1.5px solid #ababab'})
		for(var i=0;i<valid.length;i++){
			var json= valid[i];
		    var data=true;
			$.each(json, function(key, value) {
				if(key=="type"){
					if(json.type=="string"){
						var val=$("#"+json.id).val();
						if(json.mandatory==true){
							 if(val.length<=json.size){
								 $("#errorMessage").text("pleace enter atlest "+json.size+" For the "+json.displayId);
								 $("#"+json.id).css({ 'border': '2px solid red'})
								  data=false;
							 }
						}
					}
				}
	        });
	        if(data==false){
				return data;
			}
		}
		return true;
	}
</script>