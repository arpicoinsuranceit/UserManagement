<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="stylesheet"
	href="${path}/bower_components/bootstrap/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${path}/bower_components/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${path}/bower_components/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="${path}/dist/css/AdminLTE.min.css">
<link rel="stylesheet" href="${path}/dist/css/skins/skin-blue.css">
<link rel="stylesheet"
	href="${path}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
<title>${title}</title>
</head>
<body class="hold-transition skin-blue sidebar-mini" >


	<div class="wrapper">
		<jsp:include page="../../core/navigation.jsp"></jsp:include>

		<div class="content-wrapper">
			<section class="content-header">
			
			
    <div id="login-overlay" class="modal-dialog col-12" >
      <div class="modal-content">
          <div class="modal-header">
              <h4 class="modal-title" id="myModalLabel">ADD USER</h4>
              
            </div>
          
            <div class="modal-body">
              <form id="add_User_Form" method="POST" class="col-12">
            <div class="form-group">
					    <div class="col-xs-12">
                <label >User Salutation</label>
                 <select class="form-control select2" style="width: 100%;" id="selectedoption" name="selectedoption">
                  <option selected="selected" >Mr</option>
                  <option>Mis</option>
                  <option>Ms</option>
                  
                </select>
                     <br>
              </div>
                 <div class="form-group">
					    <div class="col-xs-6">
                        <label for="InputName">First Name</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="first_name" id="first_name" placeholder="Enter First Name" required>
                        <span class="input-group-addon"></span>
                        </div>
                        <br>
                        
                        <label id="nameValid" >User Code </label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="username" id="username" placeholder="Enter Username" required>
                        <span class="input-group-addon"></span>
                        
                        </div>
                        
                      
                        
                        
 <hr>
                </div>
                </div>
                    <div class="form-group">
                    <div class="col-xs-6">

                        <label for="InputName">Last Name</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="last_name" id="last_name" placeholder="Enter Last Name" required>
                        <span class="input-group-addon"></span>
                        </div>
                    
                        <br>
                        
                     
                       <label for="InputPassword">Employee Number</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="userEmployeeNo" id="userEmployeeNo" placeholder="Enter Employee Number" required>
                        <span class="input-group-addon"></span> 
                    </div>
<hr>
                </div>       
                </div> 
                
                             
                 <div class="form-group"> 
                        <div class="col-xs-6">
                        <label for="InputEmail">Mobile Number</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="mobileNumber" id="mobileNumber" placeholder="Enter Mobile Number" required>
                        <span class="input-group-addon"></span>
                    </div>
 <br>
                     </div>
                 </div>
                 
                  <div class="form-group"> 
                  
                        <div class="col-xs-6">
                        <label for="InputEmail">Telephone Number</label>
                        
                        <div class="input-group">
                        <input type="text" class="form-control" name="telephoneNumber" id="telephoneNumber" placeholder="Enter Telephone Number" required>
                        <span class="input-group-addon"></span>
                    </div>
 <br>
                     </div>
                 </div>
                    <div class="form-group"> 
                        <div class="col-xs-12">
                        <label for="InputEmail">Email</label>
                        <div class="input-group">
                        <input type="email" class="form-control" name="email" id="email" placeholder="Enter Email" required>
                        <span class="input-group-addon"></span>
                    </div>
 <br>
                     </div>
                 </div>
                 
                 <div class="form-group">
					    <div class="col-xs-6">
                        <label for="InputName">Enter NIC</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="nicpassport" id="nicpassport" placeholder="Enter NIC" required>
                        <span class="input-group-addon"></span>
                        </div>
                   
 
                </div>
                </div>
                <hr>
                     <div class="form-group">
					    <div class="col-xs-6">
                        <label for="InputName">Passport</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="passport" id="passport" placeholder="Enter Passport" required>
                        <span class="input-group-addon"></span>
                        </div>
                        

                </div>
                </div>
                 <hr>
                 <div class="form-group">
                        <div class="col-xs-12">
                        <label for="InputStreetName">Address</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="address1" id="address1" placeholder="Enter Street Name and Number" required>
                        <span class="input-group-addon"></span>
                        </div>
<!----------------------------break-------------------------------------------------------------> <br> 
                    </div>                     
                </div>
             
                        <div class="form-group">
                        <div class="col-xs-12">
                        <label for="InputCity">Address 2</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="address2" id="address2" placeholder="Enter Street Name and Number" required>
                        <span class="input-group-addon"></span>
                    </div>
<!----------------------------break-------------------------------------------------------------> <br> 
                    </div>
                    </div>

                        <div class="form-group">
                        <div class="col-xs-12">
                        <label for="InputProvince">Address 3</label>
                        <div class="input-group">
                        <input type="text" class="form-control" name="address3" id="address3" placeholder="Enter Street Name and Number" required>
                        <span class="input-group-addon"></span> 
                    </div>
<!----------------------------break-------------------------------------------------------------> <br>                     
                   </div>
                </div>

                  <div class="form-group">
                  <div class="input-group-addon">
                  <input type="button" name="submit" id="submit" value="Submit" class="btn btn-success pull-right">
                  
                  </div>
                </div>
                </form>
              </div><!---modal-body--->
          </div>
       </div>
<!--container end.//-->

			</section>
		</div>
		</div>

		<jsp:include page="../../core/footer.jsp"></jsp:include>
		<jsp:include page="../../core/SuccessAdd.jsp"></jsp:include>

		<script src="${path}/bower_components/jquery/dist/jquery.min.js"></script>
		<script
			src="${path}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="${path}/dist/js/adminlte.min.js"></script>
		<script
			src="${path}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
			
			<script type="text/javascript">
			
			
			
			$("#submit").click(function(){
				
				var Salutation=$( "#selectedoption option:selected" ).val();
				
				var userFirstName =$("#first_name").val();
				 var sysUser = {
						userSalutation: Salutation,
						userFirstName :$("#first_name").val(),
						userLastName :$("#last_name").val(),
						userNic :$("#nicpassport").val(),
						userAddress1 :$("#address1").val(),
						userAddress2 :$("#address2").val(),
						userAddress3 :$("#address3").val(),
						userEmployeeNo :$("#userEmployeeNo").val(),
						userMobileNumber :$("#mobileNumber").val(),
						userTelephoneNumber :$("#telephoneNumber").val(),
						userPassport :$("#passport").val(),
						userEmail :$("#email").val(),
						userName :$("#username").val()
			        };
				
				 $.ajax({
		                type: 'POST',
		                url: '${path}/sysUser/savesysuser',
		                data: JSON.stringify(sysUser),
		                contentType: "application/json",
		                success: function (resp) {
		                	console.log(resp)
		                	if(resp=="Work"){
		                		$("#modal-success").modal("show");
		                    }else{
		                    	alert("Error");
		                    }
		                	
		                	
		                	$("#add_User_Form .form-control").val("");	
		                }
		            });
			});
			
			$("#username").change(function() {
				var username=$("#username").val();
				
				
				$.ajax({
					type : 'get',
					url : '${path}/sysUser/get_Usercode/'+username,
					success : function(resp) {
						if(resp=="Fail"){
							document.getElementById("nameValid").innerHTML="User Code";
							document.getElementById("nameValid").style.color = "black";
							
						}else{
							document.getElementById("nameValid").innerHTML="User Code Already Exsist";
							document.getElementById("nameValid").style.color = "red";
						}
						
					},
					error : function() {
						alert('Error');
					}
				});
			});
			</script>
		
</body>
</html>
