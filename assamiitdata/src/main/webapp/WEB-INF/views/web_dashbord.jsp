<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="net.javaguides.springboot.main.utils.ConfigConstants"%>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

 <!-- Include jQuery UI library -->
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
   
<!--- date ---> 
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.css">
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.3/jquery-ui-timepicker-addon.min.js"></script>
  
<style>
body {
    margin: 0;
    padding: 0;
}

#navbar {
    display: none;
    background-color: #115368;
    position: fixed;
    top: 0;
    left: 0;
    height: 100vh;
    width: 15%;
    border-radius: 20px;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 20px;
}

li {
    margin-bottom: 10px;
}

a {
    display: block;
    color: white;
    font-size: 18px;
}
#mainDiv{
   width: 80%;
   height: 100vh;
}
#serchDiv{
    width: 104%;
/*     background-color: beige;
 */    height: 62px;
    border-bottom: 3px solid #f7f2f2;
    background: linear-gradient(to bottom, #fbf9f9, #afebff); /* Specify your two colors */
    
}
#hedlineDiv{
    width: 104%;
    margin-top: 5px;
/*     background-color: #abab18;
 */    height: 48px;
    border-bottom: 3px solid #f7f2f2;
}
#listDiv{
    width: 104%;
    height: 100vh;
/*     background-color: antiquewhite;
 */    border-bottom: 3px solid #f7f2f2;
    margin-top: 5px;
    
}
a:hover {
    color: #ddd;
}
.inpDate{
    position: relative;
    top: -56px;
    left: 180px;
    height: 35px;
    width: 300px;
    border-radius: 20px;
    background-color: #ebebeb;
}
.inpSearch{
    height: 35px;
    width: 300px;
    border-radius: 20px;
    position: relative;
    top: -56px;
    left: 326px;
    background-color: #ebebeb;
}
   input::placeholder {
          color: #999; 
          font-style: italic; 
          font-size: 18px; 
       
    }
   input:focus {
        border-color:rgb(192, 192, 192);
        outline: none; 
        color: rgb(255, 255, 255);
        background-color: #115368;
    }
   
	.inpBtn:focus{
		border-color:rgb(192, 192, 192);
        outline: none; 
        color: rgb(255, 255, 255);
        background-color: #115368;
        
	}
	.inpBtn{
	    width: 68px;
	    height: 25px;
	    border-radius: 12px;
	    position: relative;
	    left: 191px;
	    top: -57px;
	}
	 table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            border: hidden;
            
        }
      tr {
          border-bottom: 1px solid #ddd;
      }

      th, td {
/*       border: 3px solid #f7f2f2;*/          
         padding: 8px;
         text-align: left;
          
      }

      th {
          background-color: #ebebeb;
      }

        tr {
            border-bottom: 1px solid #ddd;
        }
      tr:hover {
          background-color: #f5f5f5;
      }
      .t1{
          width: 232px;
          height: 60px
      }
      .t2{
          width: 100px;
          height: 60px
      }
      .t3{
          width: 150px;
          height: 60px
      }
      .t4{
          width: 100px;
          height: 60px
      }
      .t5{
          width: 120px;
          height: 60px
      }
      .t6{
          width: 120px;
          height: 60px
      }
      .t7{
          width: 120px;
          height: 60px
      }
      .t8{
          width: 180px;
          height: 60px
      }
      .dt{
	       width: 158px  ;
		   height: 27px  ;
		   border-radius: 15px;
		   background-color: #ebebeb ;
		   
	}
	.dt::placeholder {
          color: #999; 
          font-style: italic; 
          font-size: 18px; 
       
    }
    .dtbt:focus{
		border-color:rgb(192, 192, 192);
        outline: none; 
        color: rgb(255, 255, 255);
        background-color: #115368;
	}	
	
	#listDiv{
	 /*  background: linear-gradient(60deg, #c8dce3  50%, transparent 20%),
            linear-gradient(-120deg,#d2d394 50%, transparent 20%); */
     background: linear-gradient(to bottom, #ffffff, #d6d7b5); /* Specify your two colors */

	}
	#loading-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(255, 255, 255, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 9999; /* Ensure it appears on top */
}

.loading-spinner {
  border: 4px solid rgba(0, 0, 0, 0.1);
  border-left-color: #333;
  border-radius: 50%;
  width: 40px;
  height: 40px;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
	
 
 </style>

<div  style="display: flex; align-items: center;">
   <div id="navbardiv" style="margin-right: 170px">
	   <button id="toggleBtn" class="outside">Toggle Navigation</button>
	    <nav id="navbar">
	        <ul>
	            <li><a href="https://developer.gst.gov.in/apiportal/govt/login">GST Dashbord Page</a></li>
	           <li><a href="https://www.google.com/">Google</a></li>
	              <!-- <li><a href="services">Services</a></li>
	            <li><a href="contact">Contact</a></li> -->
	        </ul>
<!-- 	           <button id="toggleBtn" class="inside">Toggle Navigation</button>
 -->	    </nav>
   </div>
   <div id="mainDiv">
	    <p id="error" style="color: red;"></p>

	   <div id="serchDiv">
	        <h1>Events</h1>     
            <input type="datetime" id="eventData" class="inpDate dtc " placeholder="Date">
	        <button id="checkboxBut"  class="inpBtn" >submit</button>
	        <input type="text" id="urlNameSearch" class="inpSearch " placeholder="Name of the URL">
	   </div>
	<!--    <div id="hedlineDiv"> 
              <div style="display: flex; align-items: center;">
                 <input type="checkbox" id="totalCheckBox">
                 <p>  Check All</p>
              </div>
       </div> -->
       <div id="listDiv">
		      <table>
			        <thead>
			            <tr>
			                <th class="t1"><input type="checkbox" id="headCheckbox" class="headCheckbox"> Check All</th>
			                <th class="t2">Name of the URL</th>
			                <th class="t3">Url</th>
			                <th class="t4">Url Key</th>
			                <th class="t5">Scheduled Date</th>
			                <th class="t6">Last Updated Date</th>
			                <th class="t7">Status</th>
			                <th class="t8 dtr">Date</th>
			            </tr>
			        </thead>
			        <tbody id="tbodyp">	
			       <!--  <tr>
			                <td class="t1"><input type="checkbox" id="totalCheckBox_1" class="checkAll"> 1</td>
			                <td class="t2" id="urlName_1">John Doe</td>
			                <td class="t3" id="url_1">john@example.com</td>
			                <td class="t4" id="dateOfUpdated_1">john@example.com</td>
			                <td class="t5" id="status_1">john@example.com</td>
			                <td class="t6" id="date_1">  
			                      <input type="text" id="datepicker_1" class="dt" placeholder="Date">
			                      <button id="dtSubmit_1" class="dtSubmit dtbt" style="border-radius: 13px">submit</button>
			                </td>
			            </tr>  -->
			            <div id="loading-overlay" style="display: none;" >
                            <div class="loading-spinner" ></div>
                        </div>
			        </tbody>
		    </table>  
		    
       </div>
   </div>
   
</div>

<script type="text/javascript">
/* document.getElementById('toggleBtn').addEventListener('click', function() {
    var navbar = document.getElementById('navbar');
    
    navbar.style.display = (navbar.style.display === 'none' || navbar.style.display === '') ? 'block' : 'none';
}); */

var basePath 				= "<%= ConfigConstants.basePath()%>";
var listJson= ${listJson}
$(document).ready(function(){
	/*  showLoading();
	setTimeout(function() {
		  hideLoading();
		}, 3000)
	  */
	$("#error").text("")
	openAppend(listJson);
    $(".dt").hide();
    $(".dtSubmit").hide();
	
	 $("#navbar").css({"display":"block"});
		  $(".outside").click(function(){
			  $("#toggleBtn").addClass("tog")
		        $("#navbar").css({"display":"block"});
		  });
	 $("#navbar").on("click",".inside", function(){
		     $("#navbar").css({"display":"none"});
		        $("#toggleBtn").removeClass("tog")
	 });
	 
	 $("#headCheckbox").click(function(){
		 if($(".headCheckbox").prop("checked")){
			 $(".checkAll").prop("checked",true);
			 $("#eventData").css({'border-color': 'rgb(192, 192, 192)','outline': 'none','color': 'rgb(255, 255, 255)','background-color': '#115368'});
			 $(".dt").hide();
			 $(".dtbt").hide();
			 $(".dtr").text("");
		 }else{			 
			 $(".checkAll").prop("checked",false);
			 $("#eventData").css({'border-color': '','outline': '','color': '','background-color': ''});
			 $(".dt").show();
			 $(".dtbt").show();
			 $(".dtr").text("Date");
		 }
	 });
	 $("#navbar").on("click",".inside", function(){
	     $("#navbar").css({"display":"none"});
	        $("#toggleBtn").removeClass("tog");
    });
	 
	 $(function() {
	      $(".dt").datetimepicker({
	        dateFormat: 'yy-mm-dd', 
	        timeFormat: 'HH:mm:ss',    
	        controlType: 'select',   
	        oneLine: true           
	      });
	      $(".dtc").datetimepicker({
		        dateFormat: 'yy-mm-dd', 
		        timeFormat: 'HH:mm:ss',    
		        controlType: 'select',   
		        oneLine: true           
		    });
	    });
	 
	 $(".check").on("click", function() {
	      var id = $(this).attr("id").split("_")[1];
		   if ($(this).prop("checked")) {
			   $("#datepicker_"+id).show();
	           $("#dtSubmit_"+id).show();
		   }else{
			   $("#datepicker_"+id).val();
			   $("#datepicker_"+id).hide();
	           $("#dtSubmit_"+id).hide();
		   } 
	});	 
	 
	 $(".dtbt").on("click", function() {
		 var id=$(this).attr("id").split("_")[1];
         var json={};
         json.sheduledId   = $.trim($("#sheduledId_"+id).val());
         json.updateDate   = $.trim($("#datepicker_"+id).val());
         json.urlKeyName   = $.trim($("#urlKeyName_"+id).val());
         showLoading();
         $.ajax({
        	  url: basePath +'/sheduled/saveSheduledData',
              method: 'POST',
              data: { data: JSON.stringify(json)}, 
              dataType: 'json',
              success: successData(id),
              error:  errorData
         });
	 });	 
	 
	  $(".inpBtn").on("click", function() {
		 var id=$(this).attr("id").split("_")[1];
         var jsonObject={};
         var listArr=[];
         if($("#eventData").val().length != 0){
	         $(".rowData").each(function(){
	        	  var id = $(this).attr("id").split("_")[1];
	        	  var json={};
	              json.updateDate    = $.trim($("#eventData").val());
	              json.sheduledId   = $.trim($("#sheduledId_"+id).val());
	              json.urlKeyName   = $.trim($("#urlKeyName_"+id).text());
	              listArr.push(json);
	         });
	         jsonObject.updateDate    = $.trim($("#eventData").val());
	         jsonObject.listData = listArr;
	         $.ajax({
	             url: basePath+'/sheduled/saveListSheduledData',
	             method: 'POST',
	             data: { data: JSON.stringify(jsonObject)}, 
	             dataType: 'json',
	             success: successListData(),
	             error:  errorListData
	         }); 
         }else{
        	 $("#error").text("Please Select the Date")
         }
	 });
	 
});

function successData(id){
	return function(response) {
		hideLoading()
		if(response.status=="1005"){
		    var styleObject = colorStructure(response.statusValue);
		    $("#status_" + id).text(response.statusValue);
		    $("#scheduledDate_"+ id).text(response.sheduledDate);
		    $("#dateOfUpdated_"+ id).text(response.updatedDate);
		    $("#status_" + id).css('color',styleObject);
		    $("#datepicker_" + id).val("");
			 window.location.href = basePath+"/dashbord";
		}
		else if(response.status=="1004"){
			$("#error_"+id).text("Date Should Be greater than present")
		}
	};
}
function errorData(response){
	alert(response)	
	alert("Error Occured ")
}

function successListData(){
	return function(response) {
		if(response.obj[0].status=="1004"){
			$("#error").text("Date Should Be greater than present")
		}else{
			var status=true;
			 for(var i=0;i<response.obj.length;i++){
				var r= response.obj[i];
				if(r.status!="1005"){
					status=false;
			        alert(r.error);		
				}	
			 } 
			 if(status){
				 window.location.href = basePath+"/dashbord";
			 }
		}
	};
}
function errorListData(response){
	alert(response)	
	alert("Error Occured ")
}

function openAppend(listJson){
	var htm='';
	var count=1;
	for(var i=0;i<listJson.length;i++){
		/*  var statusStyle = listJson[i].status === 'pending' ? 'color: red;' :
			 listJson[i].status === 'completed' ? 'color: green;' : */
         var statusStyle=colorStructure(listJson[i].status); 
		 htm+= '<tr class="rowData" id="rowData_'+count+'">';
	  //   htm+= '<td class="t1"><input type="checkbox" id="totalCheckBox_'+count+'" class="checkAll">'+listJson[i].sheduledId+'</td>';
	  	 htm+= '<td class="t1"><input type="checkbox" id="totalCheckBox_'+count+'" class="checkAll check">'+listJson[i].sheduledId+'</td>';
		 htm+= '<td class="t2" id="urlName_'+count+'">'+listJson[i].nameOfUrl+'</td>';
		 htm+= '<td class="t3" id="url_'+count+'">'+listJson[i].url+'</td>';
		 htm+= '<td class="t4" id="urlKeyName_'+count+'">'+listJson[i].urlkeyName+'</td>';
		 htm+= '<td class="t5" id="scheduledDate_'+count+'" style="color:' + statusStyle + '">'+listJson[i].sheduledDate+'</td>';
		 htm+= '<td class="t6" id="dateOfUpdated_'+count+'">'+listJson[i].updatedDate+'</td>';
		 htm+= '<td class="t7" id="status_'+count+'"   style="color:' + statusStyle + '">'+listJson[i].status+'</td>';
		 htm+= '<td class="t8" id="date_'+count+'"> ';
		 htm+= '    <p style="color:red" id="error_'+count+'"></p>';
		 htm+= '    <input type="text" id="datepicker_'+count+'" class="dt" placeholder="Date">';
		 htm+= '    <input type="text" id="sheduledId_'+count+'" value="'+listJson[i].sheduledId+'" hidden>';
		 htm+= '    <button id="dtSubmit_'+count+'" class="dtSubmit dtbt" style="border-radius: 13px">submit</button>';
		 htm+= ' </td>';
		 htm+= ' </tr>';
		 count++;
	}			                      
	 $("#tbodyp").append(htm);
}

function colorStructure(vareabel){
	 var statusStyle = {};
	    if (vareabel === "completed") {
	        statusStyle = 'blue'; 
	    } else if (vareabel === "pending") {
	        statusStyle = 'red'; 
	    } 
	    return statusStyle;
}
//Show loading symbol
function showLoading() {
  $("#loading-overlay").prop('disabled', true);
  $('#loading-overlay').fadeIn();


}

// Hide loading symbol
function hideLoading() {
  $('#loading-overlay').fadeOut();
  $("#loading-overlay").prop('disabled', false);

}
//function selectedDateAndTime(){
/* 	 $("#dtc").datetimepicker({
         dateFormat: 'yy-mm-dd',
         timeFormat: 'HH:mm:ss', // Include seconds
         minDate: 0,
         onSelect: function (selectedDateTime) {
             var currentDateTime = new Date();
             var selectedDateTimeObj = new Date(selectedDateTime);

             if (selectedDateTimeObj > currentDateTime) {
                 // Valid date and time, you can perform further actions
                 console.log("Selected date and time is greater than current date and time");
             } else {
                 // Invalid date and time, display an error or take appropriate action
                 console.log("Selected date and time must be greater than current date and time");
             }
         }
     }); */
//}
</script>