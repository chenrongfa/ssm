<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<%
	request.setAttribute("webroot", request.getContextPath());
%>
<link rel="stylesheet" type="text/css" href="${webroot}/css/list.css" />
<link rel="stylesheet" type="text/css"
	href="${webroot}/css/basic_style.css" />
<link rel="stylesheet"
	href="${webroot}/static/bootstrap-3.3.7-dist/css/bootstrap.css"
	type="text/css" />
<script src="${webroot}/jquery/jquery.js" type="text/javascript"></script>
<script src="${webroot}/static/bootstrap-3.3.7-dist/js/bootstrap.js"
	type="text/javascript"></script>
<%-- <script src="${webroot}/static/bootstrap-3.3.7-dist/js/modal.js"
	type="text/javascript"></script> --%>

<script src="${webroot }/jquery/index.js">
	
</script>
</head>
<body>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12 ">
				<h1 class="mt5 clear">ssm增删改查</h1>
			</div>
		</div>
		<!-- 增删 -->
		<div class="row">

			<div class="col-md-4 col-md-offset-8 ">
				<div class="center">

					<button class="btn btn-success btn-sm" data-target="#addModal"
						id="add_emp">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>增加
					</button>

					<button class="btn btn-warning btn-sm" id="delete_Allbtn">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
				</div>
			</div>


		</div>

		<!-- 添加数据-->
		<div class="row">

			<div class="col-md-12">
				<div class="center mt5">
					<table class="table table-hover" id="table_emp">
						<tr>
							<th><input type="checkbox" id="delete_All" checked="checked" />
								#</th>
							<th>emp_id</th>
							<th>emp_name</th>
							<th>emp_email</th>
							<th>emp_birthday</th>
							<th>部门</th>
							<th></th>
						</tr>


					</table>
				</div>
			</div>


		</div>
		<div class="row">
			<div class="center clear ">
				<div class="col-xs-6 col-md-6" id="emp_pagesRecord"></div>

				<div class="col-md-6">
					<nav aria-label="Page navigation" id="emp_nav"> </nav>

				</div>


			</div>
		</div>
	</div>
	<!--增加员工 模态框 -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工信息添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="add_form">
						<div class="form-group">
							<label for="empName" class="col-sm-4 control-label">员工姓名:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="empName"
									placeholder="员工姓名:" name="empName">
							</div>
							<span id="empName_help" class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="empEmail" class="col-sm-4 control-label">email:</label>
							<div class="col-sm-8">
								<input type="email" class="form-control" id="empEmail"
									placeholder="xxx@.com" name="empEmail">
							</div>
							<span id="empEmail_help" class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="empBirthday" class="col-sm-4 control-label">生日:</label>
							<div class="col-sm-8">
								<input type="date" class="form-control" id="empBirthday"
									placeholder="2015-1-6" name="empBirthday">
							</div>
							<span id="empBirthday_help" class="help-block"></span>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">部门:</label>
							<div class="col-sm-3">
								<select class="form-control" name="deptId" id="deptSelect">

								</select>
							</div>
						</div>


					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">Save</button>
				</div>
			</div>
		</div>
	</div>
	<!--修改员工 模态框 -->
	<div class="modal fade" id="updateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel1">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel1">员工信息修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" id="update_form">
						<div class="form-group">
							<label class="col-sm-4 control-label">员工id:</label>
							<div class="col-sm-8">
								<p class="form-control-static" id="emp_id"></p>
								<input type="hidden" class="form-control" id="empId_update"
									 name="empId">
							</div>
						</div>
						<div class="form-group">
							<label for="empName" class="col-sm-4 control-label">员工姓名:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="empName_update"
									 name="empName">
							</div>
							<span id="empName_message" class="help-block"></span>
						</div>
						<div class="form-group">
							<label for="empEmail" class="col-sm-4 control-label">email:</label>
							<div class="col-sm-8">
								<input type="empEmail_update" class="form-control" id="empEmail_update"
									placeholder="xxx@xx.com" name="empEmail">
							</div>
							<span id="empEmail_message" class="help-block">sds</span>
						</div>
						<div class="form-group">
							<label for="empBirthday_update" class="col-sm-4 control-label">生日:</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" id="empBirthday_update"
									placeholder="修改格式:1993-05-10" name="empBirthday">
							</div>
							<span id="empBirthday_message" class="help-block">dd</span>
						</div>
						<div class="form-group">
							<label class="col-sm-4 control-label">部门:</label>
							<div class="col-sm-3">
								<select class="form-control" name="deptId" id="deptupdate">

								</select>
							</div>
						</div>


					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="update">update</button>
				</div>
			</div>
		</div>
	</div>

</body>

</html>