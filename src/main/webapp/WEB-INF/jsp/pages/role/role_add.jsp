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
				<li><a href="#"><i class="fa fa-dashboard"></i> ROLE</a></li>
				<li class="active">ADD ROLE</li>
			</ol>
			</section>

			<section class="content container-fluid">

			<div class="box box-primary">

				<div class="box-header with-border">
					<h3 class="box-title">ADD ROLE</h3>
				</div>

				<div class="box-body">

					<div class="row">
						<div class="col-md-6 col-sm-12">
							<form id="form_add_role">

								<div class="form-group">
									<label>Role Name</label> <input type="text"
										class="form-control" name="name" placeholder="Enter Role Name">
								</div>

								<div class="form-group">
									<label>Description</label> <input type="text"
										class="form-control" name="description"
										placeholder="Enter Description">
								</div>

								<div class="row">
									<div class="col-xs-12">
										<div class="box">
											<div class="box-header">
												<h3 class="box-title">Menus</h3>

											</div>
											<!-- /.box-header -->
											<div class="box-body table-responsive no-padding"
												style="max-height: 200px; overflow: auto;">
												<table class="table table-hover">
													<thead>
														<tr>
															<th></th>
															<th>Menu Name</th>
															<th>Description</th>
															<th>System</th>
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
										class="btn btn-info pull-right">Add Role</button>
								</div>
							</form>
						</div>

						<div class="col-md-6 col-sm-12">
							<div class="row" style="margin-bottom: 10px;">
								<div class="col-md-6">
									<div class="input-group">
										<select class="form-control" id="select_sbu">
											<c:forEach var="sbu" items="${sbus}">
												<option value="${sbu.sbuId}">${sbu.sbuDescription}</option>
											</c:forEach>
										</select>
										<div class="input-group-btn">
											<input type="button" id="btn_sbu" class="btn btn-info"
												value="SBU" onclick="loadMenu('SBU')" />
										</div>
									</div>
								</div>

								<div class="col-md-6">
									<div class="input-group">

										<select class="form-control" id="select_system">
										</select>
										<div class="input-group-btn">
											<input type="button" id="btn_system" class="btn btn-info"
												value="System" onclick="loadMenu('SUBSBU')" />
										</div>

									</div>
								</div>

							</div>
							<div class="row">
								<div class="col-xs-12">
									<div class="box">
										<div class="box-header">
											<h3 class="box-title">Select Menu</h3>

										</div>
										<!-- /.box-header -->
										<div class="box-body table-responsive no-padding"
											style="max-height: 304px; overflow: auto;">
											<table class="table table-hover">
												<thead>
													<tr>
														<th></th>
														<th>Menu Name</th>
														<th>Description</th>
														<th>System</th>
													</tr>
												</thead>
												<tbody id="tbody_menu_select">
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


						</div>
					</div>



				</div>
			</div>

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
		<script
			src="${path}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

		<script type="text/javascript">
			function init() {
				var sbu = $("#select_sbu").val();
				getSystems(sbu);
			}

			$("#select_sbu").change(function() {
				getSystems($("#select_sbu").val());
			});

			function getSystems(sbu) {

				$.ajax({
					type : 'get',
					url : '${path}/getSubSbu/' + sbu,
					success : function(resp) {
						console.log(resp);
						$("#select_system option").remove();
						$.each(resp, function(i, item) {
							$('#select_system').append($('<option>', {
								value : item.id,
								text : item.description
							}));
						});
					},
					error : function() {
						alert('Error');
					}
				});
			}

			function loadMenu(type) {

				var id = "";

				switch (type) {
				case "SBU":
					console.log("sbu");
					id = $("#select_sbu").val();
					break;

				case "SUBSBU":

					console.log("subsbu");
					id = $("#select_system").val();
					break;

				default:
					break;
				}

				console.log(id);

				$.ajax({
					type : 'get',
					url : "${path}/role/loadMenu/" + type + "/" + id,
					success : function(resp) {
						$("#tbody_menu_select tr").remove();
						var html = "";
						if (resp != null) {
							for ( var i in resp) {
								var item = resp[i];
								var rowNum = parseInt(i) + parseInt(1);
								var row = "<tr class = \"menu_select_tr\"> \
	                                <td style=\"display: none;\">" + item.menuId + "</td>\
	                                <td><input type = \"checkbox\"/></td>\
	                                <td>" + item.menuName + "</td>\
	                                <td>" + item.menuDescription + "</td>\
	                                <td>" + item.system + "</td></tr>"; 
									html += row;
							}
						}
						$("#tbody_menu_select").append(html);
					},
					error : function() {
						alert('Error');
					}
				});
			}

			$("#button-done").click(function() {
				console.log("called");
				
				var html = "";
				$("#tbody_menu_select tr").filter(':has(:checkbox:checked)').each(function() {
					
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
                        <td>" + $(this).closest("tr").find("td:nth-child(4)").text() + "</td>\
                        <td>" + $(this).closest("tr").find("td:nth-child(5)").text() + "</td></tr>"; 
						html += row;
					}
				});
				
				$("#tbody_menu_add").append(html);
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
		</script>
</body>
</html>
