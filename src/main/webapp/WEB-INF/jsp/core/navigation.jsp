<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<body>

	<header class="main-header">
		<a href="${path}/home" class="logo"> <span class="logo-mini"><img
				src="${path}/images/logo.png" style="width: 30px; margin-top: 15px;"></span>
			<span class="logo-lg"><img src="${path}/images/logo.png"
				style="height: 40px; margin-top: 5px;"></span>
		</a>

		<nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="push-menu"
				role="button"> <span class="sr-only">Toggle navigation</span>
			</a>
			<div class="navbar-custom-menu">
				<ul class="nav navbar-nav">
					<li class="dropdown user user-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <img
							src="${path}/dist/img/user2-160x160.jpg" class="user-image"
							alt="User Image"> <span class="hidden-xs">USER</span>
					</a>
						<ul class="dropdown-menu">
							<!-- User image -->
							<li class="user-header" style="background: #213E9A"><img
								src="${path}/dist/img/user2-160x160.jpg" class="img-circle"
								alt="User Image">

								<p>
									USER <small> </small>
								</p></li>
							<!-- Menu Body -->

							<!-- Menu Footer-->
							<li class="user-footer">
								<%-- <div class="pull-right">
									<a href="${path}/logout" class="btn btn-default btn-flat">Sign out</a>
								</div> --%>
							</li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>

	<aside class="main-sidebar">

		<section class="sidebar">

			<ul class="sidebar-menu" data-widget="tree">
				<li class="header">MAIN NAVIGATION</li>

				<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
						<span>Role</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="${path}/role/addrole"><i
								class="fa fa-circle-o"></i> Add Role</a></li>
						<li><a href="${path}/role/allroles"><i
								class="fa fa-circle-o"></i> All Roles</a></li>
					</ul>
				</li>
				
				<li class="treeview"><a href="#"> <i class="fa fa-edit"></i>
						<span>Users</span> <span class="pull-right-container"> <i
							class="fa fa-angle-left pull-right"></i>
					</span>
				</a>
					<ul class="treeview-menu">
						<li><a href="${path}/sysUser/addUser "><i
								class="fa fa-circle-o"></i> Add User</a></li>
						<li><a href="${path}/user/assignroles"><i
								class="fa fa-circle-o"></i> Assign Roles </a></li>
								<li><a href="${path}/sysUser/AllUsers"><i
								class="fa fa-circle-o"></i> All Users </a></li>
								<li><a href="${path}/branch/navUserAssignBranch"><i
								class="fa fa-circle-o"></i> Assign Branch </a></li>
					</ul>
				</li>

			</ul>
		</section>
	</aside>
</body>

</html>
