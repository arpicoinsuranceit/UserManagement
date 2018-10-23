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
				ROLE <small>ADD ROLE</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="#"><i class="fa fa-dashboard"></i> USER</a></li>
				<li class="active">ASSIGN ROLE</li>
			</ol>
			</section>

			<section class="content container-fluid">

			<div class="box box-primary">

				<div class="box-header with-border">
					<h3 class="box-title">ASSIGN ROLE</h3>
				</div>

				<div class="box-body">

					<div class="row">
						<div class="col-md-6 col-sm-12">
							<div class="input-group">
								<input type="text" class="form-control" id="txt-user-search">
								<div class="input-group-btn">
									<input type="button" id="btn_search_user" class="btn btn-info"
										value="Search User" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="box">
										<div class="box-header">
											<h3 class="box-title">Select User</h3>

										</div>
										<!-- /.box-header -->
										<div class="box-body table-responsive no-padding"
											style="max-height: 304px; overflow: auto;">
											<table class="table table-hover">
												<thead>
													<tr>
														<th></th>
														<th>User Id</th>
														<th>User Name</th>
													</tr>
												</thead>
												<tbody id="tbody_user_select">
												</tbody>


											</table>
										</div>
										<!-- /.box-body -->
									</div>
									<!-- /.box -->
								</div>
							</div>
						</div>

						<div class="col-md-6 col-sm-12">
							<div class="input-group">
								<input type="text" class="form-control" id="txt-search-role">
								<div class="input-group-btn">
									<input type="button" id="btn_search_role" class="btn btn-info"
										value="Search Role" />
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="box">
										<div class="box-header">
											<h3 class="box-title">Select Role</h3>

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
							


						</div>
					</div>
					<div class="box-footer">

								<input type="button" id="button-done"
									class="btn btn-info pull-right" value="Done" />
							</div>



				</div>
			</div>

			</section>
		</div>

		<jsp:include page="../../core/footer.jsp"></jsp:include>


		<script src="${path}/bower_components/jquery/dist/jquery.min.js"></script>
		<script
			src="${path}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<script src="${path}/dist/js/adminlte.min.js"></script>
		<script
			src="${path}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
		<script
			src="${path}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

		<script type="text/javascript">
			function init() {
				getRoles("No_Val");
				getUsers("No_Val");
			}

			$("#btn_search_user").click(function() {
				getUsers($("#txt-user-search").val());
			});
			
			$("#btn_search_role").click(function() {
				getRoles($("#txt-search-role").val());
			});

			function getRoles(val) {
				
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
			
			function getUsers(val) {
				
				if (val == ""){
					val = "No_Val";
				}

				$.ajax({
					type : 'get',
					url : '${path}/user/get_user/' + val,
					success : function(resp) {
						console.log(resp);
						
						 $("#tbody_user_select tr").remove();
						
						var html = "";
						if (resp != null) {
							for ( var i in resp) {
								var item = resp[i];
								var row = "<tr class = \"menu_select_tr\"> \
	                                <td style=\"display: none;\">" + item.userId + "</td>\
	                                <td><input type = \"checkbox\"/></td>\
	                                <td>" + item.userCode + "</td>\
	                                <td>" + item.userFullName + "</td></tr>"; 
									html += row;
							}
						}
						$("#tbody_user_select").append(html); 
						
					},
					error : function() {
						alert('Error');
					}
				});
			}

			
			
			
			
			$("#button-done").click(function() {
				
				var userList = new Array();
				var roleList = new Array();
				
				$("#tbody_user_select tr").filter(':has(:checkbox:checked)').each(function() {
					var id = $(this).closest("tr").find("td:nth-child(1)").text();
					
					userList.push(id);
					
				});
				
				$("#tbody_role_select tr").filter(':has(:checkbox:checked)').each(function() {
					var id = $(this).closest("tr").find("td:nth-child(1)").text();
					
					roleList.push(id);
					
				});
				
				
				
				console.log(userList);
				console.log(roleList);
				
				var data = {
						users : userList,
						roles : roleList
				}
				console.log(data);
				
				var jsonString = JSON.stringify(data);
				
				 $.ajax({
		                type: 'POST',
		                url: '${path}/user/assign_role',
		                data: jsonString,
		                contentType: "application/json",
		                success: function (resp) {
		                    console.log(resp);
		                },
		                error: function () {
		                    alert('Error');
		                }
		            });
				
				
			});
			
			$("#button-removeMenu").click(function () {
				$("#tbody_menu_add tr").filter(':has(:checkbox:checked)').each(function() {
					$(this).closest("tr").remove();
				});
			});
			
			$("#button-addRole").click (function () {
				var data = "{";
	            $("#form_add_role .form-control").each(function () {
	                data += "\"" + $(this).attr("name") + "\" : \"" + $(this).val() + "\",";
	            });
	            data += "\"menus\" : [";
	            $("#tbody_menu_add tr").each(function() {
					data += "\"" + $(this).closest("tr").find("td:nth-child(1)").text() + "\"," 
				});
	            var jsonString = data.substring(0, data.length - 1);
	            jsonString += "]}";
	            
	            console.log(jsonString);
	            
	            $.ajax({
	                type: 'POST',
	                url: '${path}/role/addrole',
	                data: jsonString,
	                contentType: "application/json",
	                success: function (resp) {
	                    console.log(resp);
	                },
	                error: function () {
	                    alert('Error');
	                }
	            });
			});
		</script>
</body>
</html>
