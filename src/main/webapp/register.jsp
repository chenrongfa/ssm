<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<style type="text/css">
tr{
	padding-bottom:20px;
}
</style>
<script type="text/javascript">
	function add_user(){
		var par=/[0-9]{9,}@[0-9a-z]{2,3}.[a-z]{2,3}/;
		if($("#username").val()==""&&$("#password").val()==""){
			alert("用户密码不能为空");
			return false;
		}
		if($("#password").val()!=$("#oncepassword").val()){
			alert("两次密码不一致");
			return false;
		}
		if($("#email").val()==""){
			alert("邮箱不能为空");
			return false;
		}
		document.getElementById("form_add").submit();
		return true;
	}
	
</script>
</head>
<body>
	<div class="register"
		style="margin:100px auto; height: 500px;width:500px;overflow:hidden; background-image: url('${webroot}/images/bg_login.jpg');
  ">

		<form action="${webroot}/add_user" method="post"
			onsubmit="return false;" id="form_add"
			style=" margin: 150px 100px;">
			<table>
				<tr>
					<td><label for="username">用户名:</label></td>
					<td><input type="text" name="name" id="username"></td>
				</tr>
				<tr>
					<td><label for="password">密 码:</label></td>
					<td><input type="text" name="password" id="password"></td>
				</tr>
				<tr>
					<td><label for="oncepassword">确认密 码:</label></td>
					<td><input type="text" name="oncepassword" id="oncepassword"></td>
				</tr>
				<tr>
					<td><label for="email">邮箱:</label></td>
					<td><input type="email" name="email" id="email"></td>
				</tr>
				<tr align="right">
					<td colspan="2"><button type="button" onclick="add_user()">注册</button><input type="reset" value="重置"></td>
					
				</tr>
			</table>

		</form>

	</div>
</body>
</html>