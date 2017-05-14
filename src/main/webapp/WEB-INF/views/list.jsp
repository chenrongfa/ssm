<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"
	lang="zh-CH">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>查询</title>
<%
	request.setAttribute("web", request.getContextPath());
%>

<link rel="stylesheet"
	href="${web}/static/bootstrap-3.3.7-dist/css/bootstrap.css"
	type="text/css" />
<script type="text/javascript"
	src="${web}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<script type="text/javascript" src="${web}/jquery/jquery-1.4.4.min.js"></script>
<link rel="stylesheet" type="text/css" href="${web}/css/list.css" />
<link rel="stylesheet" type="text/css" href="${web}/css/basic_style.css" />
<script type="text/javascript" src="${web}/jquery/jquery-1.4.4.min.js"></script>
<%-- <link  rel="stylesheet" href="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css"/>
<script  src="<%=request.getContextPath()%>/static/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/jqury/jquery-1.4.4.min.js" type="text/javascript"></script> --%>
<script type="text/javascript">
	$(function() {

	})
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

					<button class="btn btn-success btn-sm">
						<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>增加
					</button>

					<button class="btn btn-warning btn-sm">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
				</div>
			</div>


		</div>

		<!-- 添加数据-->
		<div class="row">

			<div class="col-md-12">
				<div class="center mt5">
					<table class="table table-hover">
						<tr>
							<th>#</th>
							<th>emp_id</th>
							<th>emp_name</th>
							<th>emp_email</th>
							<th>emp_birthday</th>
							<th>部门</th>
							<th></th>
						</tr>
						<tr>
							<c:forEach items="${pageInfo.list}" var="emp">
								<tr>
									<th>#</th>
									<th>${emp.empId}</th>
									<th>${emp.empName}</th>
									<th>${emp.empEmail}</th>
									<th>${emp.empBirthday}</th>
									<th>${emp.dept.deptName}</th>
									<th><div class="center">

											<button class="btn btn-success btn-sm">
												<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>更改
											</button>

											<button class="btn btn-warning btn-sm">
												<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
											</button>
										</div></th>
								</tr>
							</c:forEach>
					</table>
				</div>
			</div>


		</div>
		<div class="row">
			<div class="center clear ">
				<div class="col-xs-6 col-md-6">

					<span class="div-center">当前页数:${pageInfo.pageNum}
						总页:${pageInfo.pages} 记录:${pageInfo.total} </span>

				</div>

				<div class="col-md-6">
					<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="${web}/emp-list?page=1">首页</a></li>
						 
						<c:if test="${pageInfo.hasPreviousPage}">
							<li><a href="${web}/emp-list?page=${pageInfo.pageNum-1}"
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						<c:if test="${!pageInfo.hasPreviousPage}">
							<li class="disabled"><a href="javascript:void(0)" aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>
						
						<c:forEach items="${pageInfo.navigatepageNums}" var="num">
							<c:if test="${num==pageInfo.pageNum}">
								<li class="active"><a href="${web}/emp-list?page=${num}">${num}</a></li>
							</c:if>
							<c:if test="${num!=pageInfo.pageNum}">
								<li><a href="${web}/emp-list?page=${num}">${num}</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${pageInfo.hasNextPage}">
							<li><a href="${web}/emp-list?page=${pageInfo.pageNum+1}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:if>
						<c:if test="${!pageInfo.hasNextPage}">
							<li class="disabled"><a href="javascript:void(0)"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span></a></li>
						</c:if>



						<li><a href="${web}/emp-list?page=${pageInfo.pages}">尾页</a></li>
						
					</ul>
					</nav>

				</div>


			</div>
		</div>
	</div>


</body>
</html>