<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	request.setAttribute("webroot", request.getContextPath());
%>
<title>Insert title here</title>
<script type="text/javascript" src="${webroot}/jquery/jquery.js"></script>
<style type="text/css">
.login:AFTER {
	content: "";
	clear: both;
	display: block;
}

input {
	margin-bottom: 20px;
}
</style>
<script type="text/javascript">
	$(function() {

		generateCode();
	});
	function goRegister() {
		alert("s");
		window.location.href = "/ssm/register";
		return false;
	}
	function login() {
		if ($("#username").val().trim() == "") {
			alert("用户或者密码不为空");
			return false;
		}
		if ($("#password").val().trim() == "") {
			alert("用户或者密码不为空");
			return false;
		}
		if ($("#imageCode").val().trim() == "") {
			alert("验证码不能为空");
			return false;
		}
		/* $.getJSON("checkCode","imageCode="+$("#imageCode").val(),function(data){
			console.log(data);
		}); */
		$.ajax({
			type: "get",
			url: "checkCode",
			data: "imageCode="+$("#imageCode").val(),
			success: function(data){
				if(data.code==100){
					/* 更新随机码 */
					generateCode();
					alert(data.dataBean.codeMessage);
					$("#imageCode").val("");
					return false;
				}
				 document.getElementById("form_user").submit(); 
			}
			
		});
	

		return true;
	}
	function generateCode() {
		$("#randCode").attr("src",
				$("#randCode").attr("src") + new Date().getMilliseconds());
	}
</script>
</head>
<body>
	<div class="login"
		style="margin:0 auto;width:300px;height:300px; background: url('${webroot}/images/bg_login.jpg') no-repeat">
		<form action="${webroot}/login"
			style="display: block; position: relative; line-height: 20px; left: 50px; top: 80px;"
			method="post" onsubmit="return false;" id="form_user">
			<table>
				<tr>
					<td><label for="username">用户名:</label></td>
					<td><input type="text" name="username" id="username">
					</td>
					</tr>
					<tr>
					<td>
					<label for="password">密 码 : </label>
					</td>
					<td>
					<input type="password" style="color:blue" name="password" id="password">
					</td>
				</tr>
				<tr>
				<td>
				<label for="imageCode">验证码:</label>
				</td>
				<td>
				<input type="text" size="5" name="imageCode" id="imageCode">
				<img id="randCode" src="${webroot}/randomCode?time="
					onclick="generateCode();" alt="点击查看">
					</td>
				</tr>
					<tr>
				<td colspan="2" align="center"><input type="button" onclick="login()" value="登录">
				<a href="${webroot}/register.jsp"><button type="button"
						onclick="goRegister()">注册</button></a>
						</td>
						</tr>
			</table>

		</form>

	</div>
</body>
</html>