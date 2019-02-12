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

<style>
<!--
	#form1 {

		display:none;

		}
-->
</style>
</head>
<body class="hold-transition skin-blue sidebar-mini" onload="init()">

	

	<div class="wrapper">
		<jsp:include page="../../core/navigation.jsp"></jsp:include>

		<div class="content-wrapper ">
        <section class="content-header ">
            <h1>
                ASSETS
                <small>ADD ASSET</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${path}/all_assets"><i class="fa fa-dashboard"></i> ASSETS</a></li>
                <li class="active">ADD ASSET</li>
            </ol>
        </section>

        <section class="content container-fluid animated fadeInLeft">

            <div class="box box-primary">

                <div class="box-header with-border">
                    <h3 class="box-title">ADD ASSET</h3>
                </div>

                <div class="box-body">
                    <form id="form_add_asset">
                        <div class="form-group">
                            <div class="row">
                                
                                <div class="col-md-6 col-xs-6">
                                    <label>SBU</label>
                                    <select class="form-control select2" style="width: 100%;" id="select_sbu" />
                                        <c:forEach var="sbu" items="${sbus}">
												<option value="${sbu.sbuId}">${sbu.sbuDescription}</option>
											</c:forEach>
                                    </select>
                                </div>
                                
                                <div class="col-md-6 col-xs-6">
                                    <label>System</label>
                                    <select class="form-control select2" style="width: 100%;" id="select_system" />
                                        
                                    </select>
                                </div>
                            </div>
                        </div>



                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-6 col-xs-6">
                                    <label>Menu Name</label>
                                    <input type="text" class="form-control" name="menuname" id="menuname" placeholder="Enter Menu Name" required />
                                </div>
                                <div class="col-md-6 col-xs-6">
                                    <label>Menu Description</label>
                                    <input type="text" class="form-control" name="menudescription" id="menudescription" placeholder="Enter Menu Description"  required />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-6 col-xs-6">
                                    <label>herf </label>
                                    <input type="text" class="form-control" name="herf" id="herf" placeholder="Enter Herf URL" />
                                </div>
                                <div class="col-md-6 col-xs-6">
                                    <label>icon Name </label>
                                    <input type="text" class="form-control" name="iconname" id="iconname" placeholder="Enter icon name" />
                                </div>
                            </div>
                        </div>

					<div class="box-footer">
                        
                            <button type="button"  id="button-addsubmenu" class="btn btn-info pull-right" ><i class="fa fa-plus"></i>&nbsp;Add SubMenu
                            </button>
                        </div>
                        
                        <form id="form1">
                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-6 col-xs-6">
                                    <label>Menu Name</label>
                                    <input type="text" class="form-control" name="smenuname" id="smenuname" placeholder="Enter Menu Name" style="display:none" required />
                                </div>
                                <div class="col-md-6 col-xs-6">
                                    <label>Menu Description</label>
                                    <input type="text" class="form-control" name="smenudescription" id="smenudescription" placeholder="Enter Menu Description" style="display:none" required />
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="row">
                                <div class="col-md-6 col-xs-6">
                                    <label>herf </label>
                                    <input type="text" class="form-control" name="sherf" id="sherf" placeholder="Enter Herf URL" style="display:none" />
                                </div>
                                <div class="col-md-6 col-xs-6">
                                    <label>icon Name </label>
                                    <input type="text" class="form-control" name="siconname" id="siconname" placeholder="Enter icon name" style="display:none"/>
                                </div>
                            </div>
                        </div>
                        <div class="box-footer">
                        
                            <button type="button"  id="sbutton-addsubmenu" class="btn btn-info pull-right" style="display:none"><i class="fa fa-plus"></i>&nbsp;Add
                            </button>
                        </div>
                        
                        </form>
                        <div class="box-body table-responsive no-padding"
												style="max-height: 200px; overflow: auto;">
												<table class="table table-hover" id="records_table">
													<thead>
														<tr>
															
															<th>Menu Name</th>
															<th>Description</th>
															<th>Herf</th>
															<th>icon</th>
														</tr>
													</thead>
													<tbody id="tbody_menu_add">
													</tbody>

												</table>
											</div>

                        <div class="box-footer">
                            <button type="button" id="cancel" class="btn btn-default" ><a href="${path}/home">Cancel</a></button>
                            <button type="button"  id="button-add" class="btn btn-info pull-right" ><i class="fa fa-plus"></i>&nbsp;Add Asset
                            </button>
                        </div>
                    </form>
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
		
		</script>
		
		<script type="text/javascript">
			function init() {
				var sbu = $("#select_sbu").val();
				getSystems(sbu);
			/* 	$smenuname.disabled = true;
				$smenudescription.disabled = true;
				$sherf.disabled = true;
				$siconname.disabled = true;
				$sbutton-addsubmenu.disabled = true; */
			}
			
			$("#button-addsubmenu").click(function(){
				$('#smenuname').show();
				$('#smenudescription').show();
				$('#sherf').show();
				$('#siconname').show();
				$('#sbutton-addsubmenu').show();
		    })
		    
		    $("#sbutton-addsubmenu").click(function(){
		    var sname= $("#smenuname").val();
		    var smenudescription= $("#smenudescription").val();
			var sherf= $("#sherf").val();
			var siconname= $("#siconname").val();
			

				$("#records_table > tbody").append("<tr><td>" + sname+ "</td><td>" + smenudescription + "</td><td>" + sherf + "</td><td>" + siconname + "</td></tr>");
			
		    })
		    
		      $("#button-add").click(function(){
		    	  
		    	  var sel = document.getElementById('select_system');
		    	
		    	  var menu = [];
		    	  $("#records_table tr").each(function() {
		    		  $(this).find("tr:gt(0)").remove();
						var data = $(this).closest("tr").find("td:nth-child(1)").text();
						var data1 = $(this).closest("tr").find("td:nth-child(2)").text();
						var data2 = $(this).closest("tr").find("td:nth-child(3)").text();
						var data3 = $(this).closest("tr").find("td:nth-child(4)").text();
						menu.push({ menuName: data, menuDescription: data1 ,href: data2, icon: data3 });
						
					});
		    	  menu.splice(0,1);
		    	 // console.log(del);
		    	  
		    	  
		    	  var menuDto = {
							
							menuName :$("#menuname").val(),
							menuDescription :$("#menudescription").val(),
							href :$("#herf").val(),
							system :sel.value,
							icon :$("#iconname").val(),
							menu:menu,
							
				        };
		    	  //
		    	  console.log(menuDto);
		    	  
		    	  $.ajax({
		                type: 'POST',
		                url: '${path}/menu/savemenu',
		                data: JSON.stringify(menuDto),
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
		    })
		    
		    function getSystems(sbu) {

				console.log("called");
				
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
		  
			</script>

</body>
</html>