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
			<h1>
				ROLE <small>EDIT ROLE</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> ROLE</a></li>
				<li class="active">EDIT ROLE</li>
			</ol>
			</section>

			<section class="content container-fluid">

			<div class="box box-primary">

				<div class="box-header with-border">
					<h3 class="box-title">EDIT ROLE</h3>
				</div>

				<div class="box-body">

					<div class="row">
						<div class="col-md-6 col-sm-12">
							<form id="form_add_role">

								<div class="form-group">
									<label>First Name</label> <input type="text" id="firstName"
										class="form-control" name="firstName" placeholder="Enter First Name">
								</div>

								<div class="form-group">
									<label>Last Name</label> <input type="text" id="lastname"
										class="form-control" name="lastname"
										placeholder="Enter Last Name">
								</div>
								
								
								<div class="form-group">
									<label>User code</label> <input type="text" id="usercode"
										class="form-control" name="usercode" placeholder="Enter User Code">
								</div>

								<div class="form-group">
									<label>Mobile Number</label> <input type="text" id="mobilenumber"
										class="form-control" name="mobilenumber"
										placeholder="Enter Mobile Number">
								</div>
								
								
								<div class="form-group">
									<label>Email</label> <input type="text" id="email"
										class="form-control" name="email" placeholder="Enter Email">
								</div>

								<div class="form-group">
									<label>Address</label> <input type="text" id="address1"
										class="form-control" name="address1"
										placeholder="Enter Adddress">
								</div>

								<div class="row">
									<div class="col-xs-12">
										<div class="box">
											<div class="box-header">
												<h3 class="box-title">User Branch</h3>

											</div>
											<!-- /.box-header -->
											<div class="box-body table-responsive no-padding"
												style="max-height: 200px; overflow: auto;">
												<table class="table table-hover" id="records_table">
													<thead>
														<tr>
															<th></th>
															<th>Branch Code</th>
															<th>Branch Name</th>
														</tr>
													</thead>
													<tbody id="tbody_menu_add">
													</tbody>

												</table>
											</div>
											<!-- /.box-body -->
										</div>
										<!-- /.box -->
									</div>
								</div>
								
								<div class="box-footer">
									<button type="button" class="btn btn-default">Cancel</button>
									<button type="button" class="btn btn-warning"  id="button-removeMenu">Remove</button>
									<button type="button" id="button-addRole"
										class="btn btn-info pull-right">update Branch</button>
								</div>
								
								<div class="row">
									<div class="col-xs-12">
										<div class="box">
											<div class="box-header">
												<h3 class="box-title">User Roles</h3>

											</div>
											<!-- /.box-header -->
											<div class="box-body table-responsive no-padding"
												style="max-height: 200px; overflow: auto;">
												<table class="table table-hover" id="userroletable">
													<thead>
														<tr>
															<th></th>
															<th>Role Name</th>
															<th>Role Description</th>
														</tr>
													</thead>
													<tbody id="tbody_userroletable">
													</tbody>

												</table>
											</div>
											<!-- /.box-body -->
										</div>
										<!-- /.box -->
									</div>
								</div>

								<div class="box-footer">
									<button type="button" class="btn btn-default">Cancel</button>
									<button type="button" class="btn btn-warning"  id="button-removeroles">Remove</button>
									<button type="button" id="button-addnewroles"
										class="btn btn-info pull-right">update Roles</button>
								</div>
								
							</form>
						</div>

						<div class="col-md-6 col-sm-12">
							
							<div class="row">
								<div class="col-xs-12">
									<div class="box">
										<div class="box-header">
											<h3 class="box-title">Select Branch</h3>

										</div>
										<!-- /.box-header -->
										<div class="box-body table-responsive no-padding"
											style="max-height: 304px; overflow: auto;">
											<table class="table table-hover">
												<thead>
													<tr>
														<th></th>
														<th>Branch Code</th>
														<th>Branch Name</th>
													</tr>
												</thead>
												<tbody id="tbody_branch_select">
												</tbody>


											</table>
										</div>
										<!-- /.box-body -->
									</div>
									<!-- /.box -->
								</div>
							</div>
							
							
							
							<div class="box-footer">

								<input type="button" id="button-done"
									class="btn btn-info pull-right" value="Done" />
							</div>


							<div class="row">
								<div class="col-xs-12">
									<div class="box">
										<div class="box-header">
											<h3 class="box-title">Select Roles</h3>

										</div>
										<!-- /.box-header -->
										<div class="box-body table-responsive no-padding"
											style="max-height: 304px; overflow: auto;">
											<table class="table table-hover">
												<thead>
													<tr>
														<th></th>
														<th>Role Name</th>
														<th>Role Description</th>
													</tr>
												</thead>
												<tbody id="tbody_role_select">
												</tbody>


											</table>
										</div>
										<!-- /.box-body -->
									</div>
									<!-- /.box -->
								</div>
							</div>
							
							<div class="box-footer">

								<input type="button" id="button-roledone"
									class="btn btn-info pull-right" value="Done" />
							</div>

						</div>
					</div>

						<div class="form-group">
                  <div class="input-group-addon">
                  <input type="button" name="submit" id="submit" value="Update User" class="btn btn-success pull-right">
                  
                  </div>
                </div>

				</div>
			</div>
<!--container end.//-->
			</section>
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
			function init() {
				getSelectedUser();
				getBranch("No_Val");
				getSelectedRole();
				getRole();
				getAllRoles("No_Val");
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
			
			
			$("#submit").click(function(){
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
			
			function getBranch(val) {
				
				if (val == ""){
					val = "No_Val";
				}

				$.ajax({
					type : 'get',
					url : '${path}/branch/getAllbranch/' + val,
					success : function(resp) {
						console.log(resp);
						
						$("#tbody_branch_select tr").remove();
						
						var html = "";
						if (resp != null) {
							for ( var i in resp) {
								var item = resp[i];
								var row = "<tr class = \"menu_select_tr\"> \
	                                <td style=\"display: none;\">" + item.id + "</td>\
	                                <td><input type = \"checkbox\"/></td>\
	                                <td>" + item.code + "</td>\
	                                <td>" + item.name + "</td></tr>"; 
									html += row;
							}
						}
						$("#tbody_branch_select").append(html);
						
					},
					error : function() {
						alert('Error');
					}
				});
			}
			
			
			$("#button-done").click(function() {
				console.log("called");
				
				var html = "";
				$("#tbody_branch_select tr").filter(':has(:checkbox:checked)').each(function() {
					
					var id = $(this).closest("tr").find("td:nth-child(1)").text();
					
					var isAvailable = false;
					
					$("#tbody_menu_add tr").each(function() {
						if($(this).closest("tr").find("td:nth-child(1)").text() == id){
							isAvailable = true;
						}
					});
					
					if(!isAvailable){
					var row = "<tr class = \"menu_select_tr\"> \
                        <td style=\"display: none;\">" + $(this).closest("tr").find("td:nth-child(1)").text() + "</td>\
                        <td><input type = \"checkbox\"/></td>\
                        <td>" + $(this).closest("tr").find("td:nth-child(3)").text() + "</td>\
                        <td>" + $(this).closest("tr").find("td:nth-child(4)").text() + "</td></tr>"; 
						html += row;
					}
				});
				
				$("#tbody_menu_add").append(html);
			});
			
			$("#button-roledone").click(function() {
				console.log("called");
				
				var html = "";
				$("#tbody_role_select tr").filter(':has(:checkbox:checked)').each(function() {
					
					var id = $(this).closest("tr").find("td:nth-child(1)").text();
					
					var isAvailable = false;
					
					$("#tbody_userroletable tr").each(function() {
						if($(this).closest("tr").find("td:nth-child(1)").text() == id){
							isAvailable = true;
						}
					});
					
					if(!isAvailable){
					var row = "<tr class = \"menu_select_tr\"> \
                        <td style=\"display: none;\">" + $(this).closest("tr").find("td:nth-child(1)").text() + "</td>\
                        <td><input type = \"checkbox\"/></td>\
                        <td>" + $(this).closest("tr").find("td:nth-child(3)").text() + "</td>\
                        <td>" + $(this).closest("tr").find("td:nth-child(4)").text() + "</td></tr>"; 
						html += row;
					}
				});
				
				$("#tbody_userroletable").append(html);
			});
			
			$("#button-removeMenu").click(function () {
				var sid= "${id}";
				 var branch = [];
				$("#tbody_menu_add tr").filter(':has(:checkbox:checked)').each(function() {
					var ids = $(this).closest("tr").find("td:nth-child(1)").text();
					branch.push(ids);
				});
				
				var branchAssignDto= {
						
		        		   branch :branch,
		        		   userid :sid,
						 };
				
				$.ajax({
					type: 'POST',
					url : '${path}/branch/remove/',
					data: JSON.stringify(branchAssignDto),
	                contentType: "application/json",
					success : function(resp) {
						if(resp=="Work"){
	                		$("#modal-success").modal("show");
	                    }else{
	                    	alert("Error");
	                    }
						
					},
					error : function() {
						alert('Error');
					}
				});
			});
			
			
		function getSelectedRole(){
				
				
	        	$.ajax({
					type : 'get',
					url : '${path}/branch/getAssignedBranch/${id}',
					success : function(resp) {
						console.log(resp);
						
						$("#tbody_menu_add tr").remove();
						var html = "";
						if (resp != null) {
							for ( var i in resp) {
								var item = resp[i];
								var rowNum = parseInt(i) + parseInt(1);
								var row = "<tr class = \"menu_select_tr\"> \
	                                <td style=\"display: none;\">" + item.id + "</td>\
	                                <td><input type = \"checkbox\"/></td>\
	                                <td>" + item.code + "</td>\
	                                <td>" + item.name + "</td>></tr>"; 
									html += row;
								
							}
							
						}
						$("#tbody_menu_add").append(html);
					},
					error : function() {
						alert('Error');
					}
				});
			} 
		
		
		$("#button-addRole").click (function () {
			
			/* console.log(${id}); */
		var ids= "${id}";
			var userdto = {
						 id : ids,
						userFirstName :$("#firstName").val(),
						userLastName :$("#lastname").val(),
						userAddress1 :$("#address1").val(),
						userMobileNumber :$("#mobilenumber").val(),
						userEmail :$("#email").val(),
						userName :$("#usercode").val()
			        };
			
			
			
			var branch = [];
            $("#tbody_menu_add tr").each(function() {
				 var data = $(this).closest("tr").find("td:nth-child(1)").text()
				 branch.push( data);
			});
           
           var branchAssignDto= {
					
        		   branch :branch,
        		   userdto :userdto,
				 };
            
            console.log(branchAssignDto);
            
		   $.ajax({
                type: 'POST',
                url: '${path}/sysUser/edituser',
                data: JSON.stringify(branchAssignDto),
                contentType: "application/json",
                success: function (resp) {
                    if(resp == "Work"){
                    	$("#modal-success").modal("show");
                    }else{
                    	alert("Error");
                    }
                    
                    $("#tbody_menu_select tr").remove();
                    $("#tbody_menu_add tr").remove();
                   	$("#form_add_role .form-control").val("");	                    
                },
                error: function () {
                    alert('Error');
                }
            });           
            
		});
		
		
		function getRole(){
			
			
        	$.ajax({
				type : 'get',
				url : '${path}/role/getuserRoles/${id}',
				success : function(resp) {
					console.log(resp);
				$("#tbody_userroletable tr").remove();
					var html = "";
					if (resp != null) {
						for ( var i in resp) {
							var item = resp[i];
							var row = "<tr class = \"menu_select_tr\"> \
                                <td style=\"display: none;\">" + item.id + "</td>\
                                <td><input type = \"checkbox\"/></td>\
                                <td>" + item.name + "</td>\
                                <td>" + item.description + "</td></tr>"; 
								html += row;
						}
							
						}
					$("#tbody_userroletable").append(html);
				},
				error : function() {
					alert('Error');
				}
			});
		} 
		
		
		function getAllRoles(val) {
			
			if (val == ""){
				val = "No_Val";
			}

			$.ajax({
				type : 'get',
				url : '${path}/role/get_role/' + val,
				success : function(resp) {
					console.log(resp);
					
					$("#tbody_role_select tr").remove();
					
					var html = "";
					if (resp != null) {
						for ( var i in resp) {
							var item = resp[i];
							var row = "<tr class = \"menu_select_tr\"> \
                                <td style=\"display: none;\">" + item.id + "</td>\
                                <td><input type = \"checkbox\"/></td>\
                                <td>" + item.name + "</td>\
                                <td>" + item.description + "</td></tr>"; 
								html += row;
						}
					}
					$("#tbody_role_select").append(html);
					
				},
				error : function() {
					alert('Error');
				}
			});
		}
		
		$("#button-removeroles").click(function () {
			var sid= "${id}";
			 var roles = [];
			$("#tbody_userroletable tr").filter(':has(:checkbox:checked)').each(function() {
				var ids = $(this).closest("tr").find("td:nth-child(1)").text();
				roles.push(ids);
			});
			
			var UserAssignDto= {
					
						roles :roles,
	        		   userid :sid,
					 };
			
			$.ajax({
				type: 'POST',
				url : '${path}/role/removeRoles/',
				data: JSON.stringify(UserAssignDto),
                contentType: "application/json",
				success : function(resp) {
					if(resp=="Work"){
                		$("#modal-success").modal("show");
                    }else{
                    	alert("Error");
                    }
					
				},
				error : function() {
					alert('Error');
				}
			});
		});
		
		
		$("#button-addnewroles").click (function () {
			
			/* console.log(${id}); */
			var ids= "${id}";
			var userdto = {
						 id : ids,
						userFirstName :$("#firstName").val(),
						userLastName :$("#lastname").val(),
						userAddress1 :$("#address1").val(),
						userMobileNumber :$("#mobilenumber").val(),
						userEmail :$("#email").val(),
						userName :$("#usercode").val()
			        };
			
			
			
			var roles = [];
            $("#tbody_userroletable tr").each(function() {
				 var data = $(this).closest("tr").find("td:nth-child(1)").text()
				 roles.push( data);
			});
           
           var userAssignDto= {
					
        		   roles :roles,
        		   userid :ids,
				 };
            
           
            
		   $.ajax({
                type: 'POST',
                url: '${path}/sysUser/edituserRoles',
                data: JSON.stringify(userAssignDto),
                contentType: "application/json",
                success: function (resp) {
                    if(resp == "Work"){
                    	$("#modal-success").modal("show");
                    }else{
                    	alert("Error");
                    }
                    
                 },
                error: function () {
                    alert('Error');
                }
            });           
            
		});
		
			</script>
		
</body>
</html>
