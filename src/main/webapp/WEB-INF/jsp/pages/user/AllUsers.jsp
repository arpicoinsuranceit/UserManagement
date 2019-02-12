<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${path}/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/bower_components/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${path}/bower_components/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${path}/bower_components/datatables.net-bs/css/dataTables.bootstrap.min.css">
    <link rel="stylesheet" href="${path}/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="${path}/dist/css/CustomStyles.css">
    <link rel="stylesheet" href="${path}/dist/css/skins/skin-blue.css">
    <title>${title}</title>
</head>
<body class="hold-transition skin-blue sidebar-mini" >

<div class="wrapper">
    <jsp:include page="../../core/navigation.jsp"></jsp:include>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                ROLE
                <small>ALL USERS</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${path}/all_supplier"><i class="fa fa-dashboard"></i>USERS</a></li>
                <li class="active">ALL USERS</li>
            </ol>
        </section>

        <section class="content container-fluid">

            <div class="row">
                <div class="col-md-3 col-sm-3 col-xs-6">
                    <a href="${path}/sysUser/addUser">
                        <button type="button" class="btn btn-block btn-success btn-flat">Add New User</button>
                    </a>
                </div>
            </div>

            <div class="row voffset10top">
                <div class="col-md-12">
                    <div class="box box-primary">
                        <div class="box-header">
                            <h3 class="box-title">USERS</h3>
                        </div>
                        <div class="box-body">

                            <table id="table_users" class="table table-bordered">
                                <thead>
                                <tr>
                                    <th>First Name</th>
                                    <th>Email</th>
                                    <th>UserCode</th>
                                    <th>Employee Number</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </thead>

                                <tbody>
                                
                                </tbody>

                                <tfoot>
                                <tr>
                                	<th>User First Name</th>
                                    <th>User Email</th>
                                    <th>User UserCode</th>
                                    <th>User Employee Number</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                                </tfoot>
                            </table>

                        </div>
                    </div>
                    <!-- /.col (right) -->
                </div>
                <!-- /.row -->
            </div>


        </section>
    </div>

    <jsp:include page="../../core/footer.jsp"></jsp:include>

    <div class="modal modal-warning fade" id="modal-danger">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Warning</h4>
                </div>
                <div class="modal-body">
                    Are You Sure ?
                </div>
                <input id="txt-delete-id" type="text" style="display: none;"/>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline pull-left" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-outline" id="button-delete">Yes</button>
                </div>
            </div>
        </div>
    </div>

    <div class="modal modal-success fade" id="modal-success">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">Success</h4>
                </div>
                <div class="modal-body">
                    Successfully Deleted....
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>

    <script src="${path}/bower_components/jquery/dist/jquery.min.js"></script>
    <script src="${path}/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${path}/dist/js/adminlte.min.js"></script>
    <script src="${path}/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
    <script src="${path}/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
    <script src="${path}/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${path}/bower_components/fastclick/lib/fastclick.js"></script>

    <script type="text/javascript">
    
    
    	/* $.ajax({
    	 type: 'get',
    	  url: '${path}/sysUser/getAllUsers',
    	  success: function (response){
    		  var c = [];
    		     $.each(response, function(i, item) {             
    		         c.push("<tr><td>" + item.userFirstName + "</td>");
    		         c.push("<td>" + item.userEmail + "</td>");
    		         c.push("<td>" + item.userName + "</td>");
    		         c.push("<td>" + item.userEmployeeNo + "</td></tr>");               
    		     });
    		     
    		     $('#table_users').html(c.join(""));
    	  }
    	}); */
    	 var table = $('#table_users').DataTable({
    	        "pageLength": 10,
    	        "ajax": "${path}/sysUser/getAllUsers",
    	        	
    	    });
    	     
    	
    
    
    	function removeUser(id) {
    		$.ajax({
				type : 'get',
				url : '${path}/sysUser/removeusers/' +id,
				success : function(resp) {
					if(resp=="Work"){
                		$("#modal-success").modal("show");
                		table.ajax.reload();
                    }else{
                    	alert("Error");
                    }
				},
				error : function() {
					alert('Error');
				}
			});
		}
    
    	
   
       
        function editRole(id) {
      
         window.location.replace("${path}/sysUser/usereditnav/"+id);
        }

       


    </script>
</body>
</html>