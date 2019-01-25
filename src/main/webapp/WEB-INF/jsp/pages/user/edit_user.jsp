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
<body class="hold-transition skin-blue sidebar-mini" onload="init()">


	<div class="wrapper">
		<jsp:include page="../../core/navigation.jsp"></jsp:include>

		<div class="content-wrapper">
			<section class="content-header">
			
			
    
         
          
            <div class="form-group">
							<label>FirstName</label> <input type="text" class="form-control" id="firstName" name="firstName"
								 placeholder="FirstName" required />
						</div>

						<div class="form-group">
							<label>LastName</label> <input type="text" id="lastname" name="lastName"
								class="form-control" 
								placeholder="LastName" required />
								
						</div>
						<div class="form-group">
							<label id="nameValid">UserCode</label> <input type="text" class="form-control" id="usercode" 
								name="usercode" placeholder="User Code"  />
						</div>

						<div class="form-group">
							<label>Employee Number</label> <input type="text" class="form-control" id="employeenumber" 
								name="employeenumber" placeholder="Employee Number" required />
						</div>

						<div class="form-group">
							<label>MobileNumber</label> <input type="text" id="mobilenumber"
								class="form-control" name=""mobilenumber""
								placeholder="MobileNumber" required />
								
						</div>
						<div class="form-group">
							<label>Telephone Number</label> <input type="text" class="form-control" id="telephonenumber"
								name="telephonenumber" placeholder="Telephone Number" required />
						</div>

						<div class="form-group">
							<label>Email</label> <input type="text" id="email"
								class="form-control" name="email"
								placeholder="Email" required />
								
						</div>
						<div class="form-group">
							<label>NIC Number</label> <input type="text" class="form-control" id="nicnumber"
								name="nicnumber" placeholder="Enter NIC" required />
						</div>

						<div class="form-group">
							<label>Passport</label> <input type="text" id="passport"
								class="form-control" name="passport"
								placeholder="Passport" required />
								
						</div>

						<div class="form-group">
							<label>Address</label> <input type="text" id="address1"
								class="form-control" name="address1"
								placeholder="Address" required />
								
						</div>
						<div class="form-group">
							<label>Passport</label> <input type="text" id="address2"
								class="form-control" name="address2"
								placeholder="Address 2" required />
								
						</div>
						
						<div class="form-group">
							<label>Passport</label> <input type="text" id="address3"
								class="form-control" name="address3"
								placeholder="Address 3" required />
								
						</div>

						<div class="box-footer">
							<button type="button" id="cancel" class="btn btn-default"><a href="${path}/home">Cancel</a></button>
							<button type="button" id="button-update"
								class="btn btn-info pull-right" ><i class="fa fa-plus"></i>&nbsp;Update User</button>
						</div>
          </div>
       </div>
<!--container end.//-->

			</section>
		

		<jsp:include page="../../core/footer.jsp"></jsp:include>
		<jsp:include page="../../core/SuccessAdd.jsp"></jsp:include>

		<script src="${path}/bower_components/jquery/dist/jquery.min.js"></script>
		<script
			src="${path}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="${path}/dist/js/adminlte.min.js"></script>
		<script
			src="${path}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
			
			<script type="text/javascript">
			function init() {
				getSelectedUser();
			}
			
			function getSelectedUser(){
				$.ajax({
					type : 'get',
					url : '${path}/sysUser/getSelectUser/${id}',
					success : function(resp) {
						$('#firstName').val(resp.userFirstName);
						$('#lastname').val(resp.userLastName);
						$('#usercode').val(resp.userName);
						$('#employeenumber').val(resp.userEmployeeNo);
						$('#mobilenumber').val(resp.userMobileNumber);
						$('#telephonenumber').val(resp.userTelephoneNumber);
						$('#email').val(resp.userEmail);
						$('#passport').val(resp.userPassport);
						$('#address1').val(resp.userAddress1);
						$('#address2').val(resp.userAddress2);
						$('#address3').val(resp.userAddress2);
					},
				});
			}
			
			
			$("#button-update").click(function(){
				var ids= "${id}";
				
				 var Salutation=$( "#selectedoption option:selected" ).val();
				
				var userFirstName =$("#first_name").val();
				 var sysUser = {
						 id : ids,
						userFirstName :$("#firstName").val(),
						userLastName :$("#lastname").val(),
						userNic :$("#nicpassport").val(),
						userAddress1 :$("#address1").val(),
						userAddress2 :$("#address2").val(),
						userAddress3 :$("#address3").val(),
						userEmployeeNo :$("#employeenumber").val(),
						userMobileNumber :$("#mobilenumber").val(),
						userTelephoneNumber :$("#telephonenumber").val(),
						userPassport :$("#passport").val(),
						userEmail :$("#email").val(),
						userName :$("#usercode").val()
			        };
				
				 $.ajax({
		                type: 'POST',
		                url: '${path}/sysUser/updatesysuser',
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
			
			$("#usercode").change(function() {
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
